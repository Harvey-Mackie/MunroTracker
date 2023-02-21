package com.munro.api.mapper;

import com.munro.api.model.dto.WeatherDtoTemp;
import com.munro.api.model.response.MetOfficeSiteRep;
import com.munro.api.model.response.MetOfficeWeatherRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapMetOfficeSiteRepToWeatherDto {

    private static Logger logger = LoggerFactory.getLogger(MapMetOfficeSiteRepToWeatherDto.class);

    public static Optional<List<WeatherDtoTemp>> tryMap(MetOfficeSiteRep metOfficeSiteRep){
        try{

            List<WeatherDtoTemp> response = new ArrayList<>();
            metOfficeSiteRep
                    .getMetOfficeDV()
                    .getMetOfficeLocation()
                    .getMetOfficeWeatherPeriod()
                    .forEach(period -> {
                        MetOfficeWeatherRep dayPeriod = period.getMetOfficeWeatherReps().stream().findFirst().get();
                        WeatherDtoTemp dto = new WeatherDtoTemp();

                        dto.setDescription(getWeatherTypeFromCode(dayPeriod.getW()));
                        dto.setVisibility(getVisibilityFromCode(dayPeriod.getV()));
                        dto.setTemp(dayPeriod.getDm());
                        dto.setTempFeelsLike(dayPeriod.getFDm());
                        dto.setDate(period.getDate());

                        response.add(dto);
                    });
            return Optional.ofNullable(response);
        }catch(Exception ex){
            logger.error("Failed to map MetOfficeSiteRep to WeatherDto", ex);
        }
        return Optional.empty();
    }

    private static String getWeatherTypeFromCode(String code){
        int codeInt = Integer.parseInt(code);

        if(codeInt == 1){
            return "Sunny";
        }
        else if(codeInt < 9){
            return "Cloudy";
        }
        else if(codeInt < 22){
            return "Rain";
        }
        else if(codeInt < 28){
            return "Snow";
        }
        else{
            return "Thunder";
        }
    }

    private static String getVisibilityFromCode(String code){
        switch(code){
            case "UN": return "Unknown";
            case "VP": return "Very Poor";
            case "PO": return "Poor";
            case "MO": return "Moderate";
            case "GO": return "Good";
            case "VG": return "Very Good";
            case "EX": return "Excellent";
        }
        return "";
    }
}
