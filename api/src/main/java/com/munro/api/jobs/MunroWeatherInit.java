package com.munro.api.jobs;

import com.munro.api.model.entities.MunroWeatherEntity;
import com.munro.api.repository.MunroRepository;
import com.munro.api.repository.MunroWeatherRepository;
import com.munro.api.service.MunroService;
import com.munro.api.service.external.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MunroWeatherInit {

    private final Logger logger = LoggerFactory.getLogger(MunroWeatherInit.class);

    @Autowired
    private MunroService munroService;
    @Autowired
    private MunroRepository munroRepository;
    @Autowired
    private MunroWeatherRepository munroWeatherRepository;
    @Autowired
    private WeatherService weatherService;

    public MunroWeatherInit(MunroService munroService, MunroRepository munroRepository, MunroWeatherRepository munroWeatherRepository, WeatherService weatherService) {
        this.munroService = munroService;
        this.munroRepository = munroRepository;
        this.munroWeatherRepository = munroWeatherRepository;
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 86400000, initialDelay = 0)
    public void initWeather(){
        logger.info("Fetching weather");

        var munros = munroRepository.findAll();

        if(munros.isEmpty()){
            munroService.fetchMunros();
            munros = munroRepository.findAll();
        }

        munros.forEach(munro -> {
            logger.info("Attempting to fetch weather for {}", munro.getName());

            try {
                var weatherDtoTemps = weatherService.getWeatherForNext5Days(munro.getName());

                for(var weatherDto : weatherDtoTemps){
                    munroWeatherRepository.save(new MunroWeatherEntity(
                            weatherDto.getDescription(),
                            weatherDto.getDate(),
                            weatherDto.getVisibility(),
                            Double.parseDouble(weatherDto.getTemp()),
                            Double.parseDouble(weatherDto.getTempFeelsLike()),
                            munro
                    ));
                }
            } catch (Exception e) {
                logger.info("Failed to fetch weather for {}", munro.getName());
            }


            logger.info("Successfully fetched weather for {}", munro.getName());
        });

        logger.info("Successfully fetched weather");
    }
}
