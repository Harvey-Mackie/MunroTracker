package com.munro.api.model.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DV")
public class MetOfficeDV {
    @XmlElement(name = "Location")
    public MetOfficeWeatherLocation getMetOfficeLocation() {
        return metOfficeLocation;
    }

    public void setMetOfficeLocation(MetOfficeWeatherLocation metOfficeLocation) {
        this.metOfficeLocation = metOfficeLocation;
    }

    private MetOfficeWeatherLocation metOfficeLocation;
}
