package com.munro.api.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class MunroDetailsDto {
    private String name;
    private int height;
    private double latitude;
    private double longitude;
    private String region;
    private String meaningOfName;
    private boolean completed;
    private List<MunroCompletedCommentDto> munroCompletedCommentDtoList;
    private List<MunroCompletedKudosDto> munroCompletedKudosDtoList;

    private List<WeatherDtoTemp> munroWeather;

    public MunroDetailsDto(String name, int height, double latitude, double longitude, String region, String meaningOfName, boolean completed) {
        this.name = name;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.meaningOfName = meaningOfName;
        this.completed = completed;
    }
}
