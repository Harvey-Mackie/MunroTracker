package com.munro.api.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public MunroDetailsDto(String name, int height, double latitude, double longitude, String region, String meaningOfName, boolean completed, List<MunroCompletedCommentDto> munroCompletedCommentDtoList, List<MunroCompletedKudosDto> munroCompletedKudosDtoList) {
        this.name = name;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.meaningOfName = meaningOfName;
        this.completed = completed;
        this.munroCompletedCommentDtoList = munroCompletedCommentDtoList;
        this.munroCompletedKudosDtoList = munroCompletedKudosDtoList;
    }

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
