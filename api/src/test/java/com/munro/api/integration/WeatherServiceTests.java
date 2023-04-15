package com.munro.api.integration;

import com.munro.api.ApiApplication;
import com.munro.api.model.dto.MunroDetailsDto;
import com.munro.api.service.MunroService;
import com.munro.api.service.external.WeatherService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties"
)
class WeatherServiceTests {

    @Autowired
    private MunroService munroService;
    @Autowired
    private WeatherService weatherService;

    @Test
    void shouldReturnWeatherForMunro(){
        var munro = new MunroDetailsDto("Ben Nevis", 10, 10, 10, "Perth", "No meaning", false);

        try{
            var weatherResponse = weatherService.getWeatherForNext5Days(munro.getName());
            Assert.assertNotNull(weatherResponse);
            Assert.assertNotNull(weatherResponse.get(0).getDescription());
            Assert.assertNotNull(weatherResponse.get(0).getDate());
            Assert.assertNotNull(weatherResponse.get(0).getTemp());
            Assert.assertNotNull(weatherResponse.get(0).getTempFeelsLike());
            Assert.assertNotNull(weatherResponse.get(0).getVisibility());
        }
        catch(Exception ex){
            Assert.fail();
        }
    }
}
