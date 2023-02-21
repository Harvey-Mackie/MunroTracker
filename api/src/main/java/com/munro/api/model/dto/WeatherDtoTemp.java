package com.munro.api.model.dto;

public class WeatherDtoTemp{
    private String description;
    private String date;
    private String visibility;
    private String temp;
    private String tempFeelsLike;

    public WeatherDtoTemp() {}

    public WeatherDtoTemp(String description, String day, String visibility, String temp, String tempFeelsLike) {
        this.description = description;
        this.date = day;
        this.visibility = visibility;
        this.temp = temp;
        this.tempFeelsLike = tempFeelsLike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempFeelsLike() {
        return tempFeelsLike;
    }

    public void setTempFeelsLike(String tempFeelsLike) {
        this.tempFeelsLike = tempFeelsLike;
    }
}