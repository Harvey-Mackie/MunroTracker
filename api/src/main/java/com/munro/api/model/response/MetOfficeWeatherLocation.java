package com.munro.api.model.response;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class MetOfficeWeatherLocation {
    private List<MetOfficeWeatherPeriod> metOfficeWeatherPeriod;
    @XmlElement(name = "Period")
    public List<MetOfficeWeatherPeriod> getMetOfficeWeatherPeriod() {
        return metOfficeWeatherPeriod;
    }

    public void setMetOfficeWeatherPeriod(List<MetOfficeWeatherPeriod> metOfficeWeatherPeriod) {
        this.metOfficeWeatherPeriod = metOfficeWeatherPeriod;
    }
}
