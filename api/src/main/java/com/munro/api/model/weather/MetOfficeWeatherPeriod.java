package com.munro.api.model.weather;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class MetOfficeWeatherPeriod {

    @XmlElement(name = "Rep")
    public List<MetOfficeWeatherRep> getMetOfficeWeatherReps() {
        return metOfficeWeatherReps;
    }

    @XmlAttribute(name = "value")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setMetOfficeWeatherReps(List<MetOfficeWeatherRep> metOfficeWeatherReps) {
        this.metOfficeWeatherReps = metOfficeWeatherReps;
    }
    private String date;
    private List<MetOfficeWeatherRep> metOfficeWeatherReps;
}


