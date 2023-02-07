package com.munro.api.model.dto;

import java.util.List;

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

    public MunroDetailsDto() {
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMeaningOfName() {
        return meaningOfName;
    }

    public void setMeaningOfName(String meaningOfName) {
        this.meaningOfName = meaningOfName;
    }

    public List<MunroCompletedCommentDto> getMunroCompletedCommentDtoList() {
        return munroCompletedCommentDtoList;
    }

    public void setMunroCompletedCommentDtoList(List<MunroCompletedCommentDto> munroCompletedCommentDtoList) {
        this.munroCompletedCommentDtoList = munroCompletedCommentDtoList;
    }

    public List<MunroCompletedKudosDto> getMunroCompletedKudosDtoList() {
        return munroCompletedKudosDtoList;
    }

    public void setMunroCompletedKudosDtoList(List<MunroCompletedKudosDto> munroCompletedKudosDtoList) {
        this.munroCompletedKudosDtoList = munroCompletedKudosDtoList;
    }
}
