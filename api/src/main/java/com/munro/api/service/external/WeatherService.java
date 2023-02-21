package com.munro.api.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.munro.api.exception.SiteNotFoundException;
import com.munro.api.mapper.MapMetOfficeSiteRepToWeatherDto;
import com.munro.api.model.dto.WeatherDtoTemp;
import com.munro.api.model.response.MetOfficeSiteCollection;
import com.munro.api.model.response.MetOfficeSiteRep;
import com.munro.api.properties.ConfigProperties;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private HttpClient httpClient;
    
    @Autowired
    protected final ConfigProperties configProperties;

    public WeatherService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
        httpClient = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public List<WeatherDtoTemp> getWeatherForNext5Days(String munroName){
        Map<String, Integer> siteLocations = getWeatherSiteLocations();

        if(!siteLocations.containsKey(munroName)){
            logger.info("Site does not exist with name " + munroName + ".");
            throw new SiteNotFoundException(munroName);
        }

        int siteLocationId = siteLocations.get(munroName);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(buildGet5DayWeatherForecast(siteLocationId)))
                .GET()
                .build();

        try{
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() > 300 || response.statusCode() < 200){
                throw new RuntimeException("Exception occurred: " + response.body());
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(MetOfficeSiteRep.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(response.body());
            MetOfficeSiteRep metOfficeSiteRep = (MetOfficeSiteRep) unmarshaller.unmarshal(reader);

            var weatherDto = MapMetOfficeSiteRepToWeatherDto.tryMap(metOfficeSiteRep);

            logger.info("Successfully unmarshalled weather data.");

            return weatherDto.get();

        }
        catch (Exception exception){
            logger.error("An error occured while getting the weather", exception);
        }

        return new ArrayList<>();
    }

    @SneakyThrows
    private Map<String, Integer> getWeatherSiteLocations(){
        Map<String,Integer> weatherSiteLocations = new HashMap<>();
        String url = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/datatype/sitelist?key=" + configProperties.weatherApiKey;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(MetOfficeSiteCollection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(response.body());
            MetOfficeSiteCollection siteLocations = (MetOfficeSiteCollection) unmarshaller.unmarshal(reader);

            siteLocations.getLocations().stream().forEach(l -> weatherSiteLocations.put(l.getName(), l.getId()));
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }


        return weatherSiteLocations;
    }

    private String buildGet5DayWeatherForecast(int locationId) {
        return "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/xml/" + locationId + "?res=daily&key=" + configProperties.weatherApiKey;
    }
}

