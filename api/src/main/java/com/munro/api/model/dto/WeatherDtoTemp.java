package com.munro.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDtoTemp{
    private String description;
    private String date;
    private String visibility;
    private String temp;
    private String tempFeelsLike;
}