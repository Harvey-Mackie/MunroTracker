package com.munro.api.service;

import com.munro.api.model.domain.MunroCompletedEntity;
import com.munro.api.model.domain.MunroEntity;
import com.munro.api.model.dto.MunroDetailsDto;
import com.munro.api.properties.ConfigProperties;
import com.munro.api.repository.MunroCompletedRepository;
import com.munro.api.repository.MunroRepository;
import com.munro.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MunroService {

    private final Logger logger = LoggerFactory.getLogger(MunroService.class);
    private final MunroRepository munroRepository;
    private final MunroCompletedRepository munroCompletedRepository;
    private final UserRepository userRepository;
    protected final ConfigProperties configProperties;

    @Autowired
    public MunroService(MunroRepository munroRepository, ConfigProperties configProperties, MunroCompletedRepository munroCompletedRepository, UserRepository userRepository){
        this.munroRepository = munroRepository;
        this.configProperties = configProperties;
        this.munroCompletedRepository = munroCompletedRepository;
        this.userRepository = userRepository;
    }

    public void FetchMunros() {
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
            munroRepository.deleteAll();

            var fileName = System.getProperty("user.dir") + "\\src\\main\\java\\com\\munro\\api\\data\\munros.csv";
            var br = new BufferedReader(new FileReader(fileName));
            while((line = br.readLine()) != null && lineCount < configProperties.munroCountCap){
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

                logger.info("Processed " + data[1] + " munro.");

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
        logger.info("Successfully populated databases with " + lineCount + " munros.");
    }

    public List<MunroDetailsDto> GetMunros(Long userId){
        logger.info("Attempting to retrieve the munros.");
        var munros = this.munroRepository.findAll();
        var user = userRepository.findById(userId).get();
        var completedMunros = user.getMunroCompletedEntities();

        var munroResponse = new ArrayList<MunroDetailsDto>();
        for(var munro : munros){
            var hasCompletedMunro = completedMunros
                    .stream()
                    .filter(p -> p.getMunro_id() == munro.getId()).count() > 0;

            munroResponse.add(new MunroDetailsDto(
                    munro.getName(),
                    munro.getHeight(),
                    munro.getLatitude(),
                    munro.getLongitude(),
                    munro.getRegion(),
                    munro.getMeaningOfName(),
                    hasCompletedMunro ? true : false
            ));
        }

        logger.info("Successfully retrieved the munros.");
        return munroResponse;
    }

    public void SetMunroToComplete(Long userId, Long munroId, LocalDate date) {
        logger.info("Attempting to set munro to completed.");
        var user = userRepository.findById(userId);

        if(user.isPresent() == false){
            logger.info("Failed to find a user with the id " + userId);
            throw new RuntimeException("Failed to find user with id " + userId);
        }

        munroCompletedRepository.save(
                new MunroCompletedEntity(user.get(), munroId, date)
        );

        logger.info("Successfully set munro to completed.");

    }
}
