package com.munro.api.model.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Locations")
public class MetOfficeSiteCollection {

    private List<MetOfficeSiteLocation> locations;

    @XmlElement(name = "Location")
    public List<MetOfficeSiteLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<MetOfficeSiteLocation> locations) {
        this.locations = locations;
    }
}
