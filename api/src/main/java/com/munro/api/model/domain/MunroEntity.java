package com.munro.api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class MunroEntity {

    @Id
    @SequenceGenerator(
            name = "munro_sequence",
            sequenceName = "munro_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "munro_sequence"
    )
    private Long id;
    private String name;
    private int height;

    public MunroEntity(String name, int height, double latitde, double longitude, String region, String meaningOfName) {
        this.name = name;
        this.height = height;
        this.latitude = latitde;
        this.longitude = longitude;
        this.region = region;
        this.meaningOfName = meaningOfName;
    }

    private double latitude;
    private double longitude;
    private String region;
    private String meaningOfName;

    @OneToMany(mappedBy = "munro", fetch = FetchType.EAGER)
    private List<MunroCompletedEntity> munroCompletedEntities;
    @OneToMany(mappedBy = "munro", fetch = FetchType.LAZY)
    private List<MunroWeatherEntity> munroWeatherEntities;
}
