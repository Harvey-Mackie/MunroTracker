package com.munro.api.service;

import com.munro.api.model.entities.*;
import com.munro.api.model.dto.*;
import com.munro.api.properties.ConfigProperties;
import com.munro.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.munro.api.utils.MunroServiceUtil.mapMunroCompletedEntityToMunroFeedDetailsDto;

@Service
@RequiredArgsConstructor
public class MunroService {

    private final Logger logger = LoggerFactory.getLogger(MunroService.class);
    @Autowired
    private final MunroRepository munroRepository;
    @Autowired
    private final MunroWeatherRepository munroWeatherRepository;
    @Autowired
    private final MunroCompletedRepository munroCompletedRepository;
    @Autowired
    private final MunroCompletedKudosRepository munroCompletedKudosRepository;

    @Autowired
    private final MunroCompletedCommentRepository munroCompletedCommentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    protected final ConfigProperties configProperties;

    public void fetchMunros() {
        logger.info("Attempting to fetch munros from CSV file and populate the database.");

        if(!configProperties.refreshMunroDatabase){
            logger.info("Skipping populating the database as the refreshMunroDatabase configuration property is set to false.");
            return;
        }

        String line = "";
        String splitBy = ",";
        List<MunroEntity> munroEntities = new ArrayList<>();
        int lineCount = 0;

        try {
            munroWeatherRepository.deleteAll();
            munroRepository.deleteAll();

            var fileName = System.getProperty("user.dir") + "\\src\\main\\java\\com\\munro\\api\\data\\munros.csv";
            var br = new BufferedReader(new FileReader(fileName));
            while((line = br.readLine()) != null && lineCount <= configProperties.munroCountCap){
                if(lineCount == 0){
                    lineCount++;
                    continue;
                }

                String[] data = line.split(splitBy);

                if(data.length < 7){
                    throw new RuntimeException("Failed at reading Munro csv, expected 8 columns but only retrieved " + data.length);
                }
                munroEntities.add(
                        new MunroEntity(
                                data[1],
                                Integer.parseInt(data[2]),
                                Double.parseDouble(data[3]),
                                Double.parseDouble(data[4]),
                                data[6],
                                data[5]
                        )
                );

                logger.info("Processed {} munro.", data[1]);

                lineCount++;
            }
        } catch (FileNotFoundException e) {
            logger.error("Failed to find the file - please ensure the fileName is correct.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed to process file - encountered the following IO Exception; " + e.getMessage());
            throw new RuntimeException(e);
        }

        munroRepository.saveAll(munroEntities);
        logger.info("Successfully populated databases with {} munros.", lineCount);
    }

    @Transactional
    public List<MunroDetailsDto> getMunros(Long userId){
        logger.info("Attempting to retrieve the munros.");
        var munros = this.munroRepository.findAll();
        var userExists = userRepository.findById(userId).isPresent();

        var user = userExists ? userRepository.findById(userId).get() : null;

        var munroResponse = new ArrayList<MunroDetailsDto>();
        for(var munro : munros){
            var hasCompletedMunro = userExists ? user
                    .getMunroCompletedEntities()
                    .stream()
                    .filter(p -> p.getMunro().getId() == munro.getId()).count() > 0 : false;

            var dto = new MunroDetailsDto(
                    munro.getName(),
                    munro.getHeight(),
                    munro.getLatitude(),
                    munro.getLongitude(),
                    munro.getRegion(),
                    munro.getMeaningOfName(),
                    hasCompletedMunro ? true : false
            );

            List<WeatherDtoTemp> weatherDtoTemps = new ArrayList<>();
            for(var weatherEntity : munro.getMunroWeatherEntities()){
                weatherDtoTemps.add(new WeatherDtoTemp(
                        weatherEntity.getDescription(),
                        weatherEntity.getDate(),
                        weatherEntity.getVisibility(),
                        weatherEntity.getTemp().toString(),
                        weatherEntity.getTempFeelsLike().toString()
                ));
            }

            dto.setMunroWeather(weatherDtoTemps);

            munroResponse.add(dto);
        }

        logger.info("Successfully retrieved the munros.");
        return munroResponse;
    }

    public Long setMunroToComplete(Long userId, Long munroId, LocalDate date) {
        logger.info("Attempting to set munro to completed.");

        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            logger.info("Failed to find a user with the id {}.", userId);
            throw new RuntimeException("Failed to find user with id " + userId);
        }

        var munro = munroRepository.findById(munroId);
        if(munro.isEmpty()){
            logger.info("Failed to find a munro with the id {}.", munroId);
            throw new RuntimeException("Failed to find munro with id " + munroId);
        }

        var completedEntity = munroCompletedRepository.save(
                new MunroCompletedEntity(user.get(), munro.get(), date)
        );

        logger.info("Successfully set munro to completed.");

        return completedEntity.getId();
    }

    public List<MunroFeedDetailsDto> feed(Long currentUserId) {
        var currentUser = userRepository.findById(currentUserId);

        if(currentUser.isEmpty()){
            throw new RuntimeException("Failed to find user with id " + currentUserId);
        }

        List<MunroFeedDetailsDto> feed = new ArrayList<>();
        currentUser.get().getUserFollowing().forEach(
                user -> user.getTo().getMunroCompletedEntities().forEach(
                        completedMunro -> feed.add(mapMunroCompletedEntityToMunroFeedDetailsDto(completedMunro))));

        return feed;
    }

    public MunroFeedDetailsDto getMunroCompleted(Long munroCompletedId) {
        var munroCompletedEntry = munroCompletedRepository.findById(munroCompletedId);

        if(munroCompletedEntry.isEmpty()){
            throw new RuntimeException("Failed to find Munro Completed entry with id" + munroCompletedId);
        }

        var completedMunro = munroCompletedEntry.get();
        var munroFeedDetails = mapMunroCompletedEntityToMunroFeedDetailsDto(completedMunro);

        List<MunroCompletedKudosDto> kudosEntries = new ArrayList<MunroCompletedKudosDto>();
        completedMunro.getMunroCompletedKudosEntities().forEach(kudos ->
            kudosEntries.add(
                    new MunroCompletedKudosDto(
                            kudos.getMunroCompleted().getId(),
                            kudos.getUser().getName(),
                            kudos.getUser().getId(),
                            kudos.getDate()))
        );

        munroFeedDetails.setMunroCompletedKudosDtoList(kudosEntries);

        return munroFeedDetails;
    }

    public List<WeatherDtoTemp> getWeatherForecast(Long munroId){
        var munro = munroRepository.findById(munroId);


        var exampleWeather = new MunroWeatherEntity();
        exampleWeather.setMunro(munro.get());

        var weatherEntities = munroWeatherRepository.findAll(Example.of(exampleWeather));
        List<WeatherDtoTemp> weatherDtoTemps = new ArrayList<>();
        for(var weather : weatherEntities){
            weatherDtoTemps.add(
                    new WeatherDtoTemp(weather.getDescription(), weather.getDate(), weather.getVisibility(), weather.getTemp().toString(), weather.getTempFeelsLike().toString())
            );
        }

        return weatherDtoTemps;
    }

    public void giveKudos(Long munroCompletedId, Long currentUserId) {
        logger.info("Attempting to give Kudos on munroCompleted entry {} from user {}.", munroCompletedId, currentUserId);

        var munroCompletedEntry = munroCompletedRepository.findById(munroCompletedId);
        if(munroCompletedEntry.isEmpty()){
            logger.info("Failed to find munroCompleted entry {}.", munroCompletedId);
            throw new RuntimeException("Failed to find Munro Completed entry with id" + munroCompletedId);
        }

        var currentUser = userRepository.findById(currentUserId);
        if(currentUser.isEmpty()){
            logger.info("Failed to find user entry {}.", currentUserId);
            throw new RuntimeException("Failed to find user entry with id" + currentUserId);
        }

        var kudoEntry = new MunroCompletedKudosEntity(munroCompletedEntry.get(), currentUser.get(), LocalDateTime.now());
        munroCompletedKudosRepository.save(kudoEntry);

        logger.info("Successfully gave Kudos on munroCompleted entry {} from user {}.", munroCompletedId, currentUserId);
    }

    public void postComment(Long munroCompletedId, Long currentUserId, String comment){
        logger.info("Attempting to post a comment from user {} on munro completed entry {}.", munroCompletedId, currentUserId);

        var munroCompletedEntry = munroCompletedRepository.findById(munroCompletedId);
        if(munroCompletedEntry.isEmpty()){
            logger.info("Failed to find munroCompleted entry {}.", munroCompletedId);
            throw new RuntimeException("Failed to find Munro Completed entry with id" + munroCompletedId);
        }

        var currentUser = userRepository.findById(currentUserId);
        if(currentUser.isEmpty()){
            logger.info("Failed to find user entry {}.", currentUserId);
            throw new RuntimeException("Failed to find user entry with id" + currentUserId);
        }

        var commentEntity = new MunroCompletedCommentEntity(munroCompletedEntry.get(), currentUser.get(), LocalDateTime.now(), comment);
        munroCompletedCommentRepository.save(commentEntity);

        logger.info("Successfully posted a comment from user {} on munro completed entry {}", currentUserId, munroCompletedId);
    }
}
