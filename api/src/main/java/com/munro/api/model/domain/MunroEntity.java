package com.munro.api.model.domain;

import jakarta.persistence.*;

@Entity
@Table
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

    public MunroEntity() {
    }

    public MunroEntity(String name, int height, double latitde, double longitude, String region, String meaningOfName) {
        this.name = name;
        this.height = height;
        this.latitude = latitde;
        this.longitude = longitude;
        this.region = region;
        this.meaningOfName = meaningOfName;
    }

    private double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    private double longitude;
    private String region;
    private String meaningOfName;
}
