package com.munro.api.model.domain;

import jakarta.persistence.*;

@Entity
@Table
public class MunroWeatherEntity {

    public MunroWeatherEntity() {
    }

    @Id
    @SequenceGenerator(
            name = "munro_weather_sequence",
            sequenceName = "munro_weather_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "munro_weather_sequence"
    )
    private Long id;
    private String description;
    private String date;
    private String visibility;
    private Double temp;
    private Double tempFeelsLike;

    public MunroWeatherEntity(String description, String date, String visibility, Double temp, Double tempFeelsLike, MunroEntity munro) {
        this.description = description;
        this.date = date;
        this.visibility = visibility;
        this.temp = temp;
        this.tempFeelsLike = tempFeelsLike;
        this.munro = munro;
    }

    @ManyToOne
    @JoinColumn(name = "munro_id", referencedColumnName = "id")
    private MunroEntity munro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempFeelsLike() {
        return tempFeelsLike;
    }

    public void setTempFeelsLike(Double tempFeelsLike) {
        this.tempFeelsLike = tempFeelsLike;
    }

    public MunroEntity getMunro() {
        return munro;
    }

    public void setMunro(MunroEntity munro) {
        this.munro = munro;
    }
}
