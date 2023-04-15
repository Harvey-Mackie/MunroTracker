package com.munro.api.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class MunroWeatherEntity {
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
}
