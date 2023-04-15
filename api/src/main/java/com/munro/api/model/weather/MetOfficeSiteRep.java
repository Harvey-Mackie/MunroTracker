package com.munro.api.model.weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SiteRep")
public class MetOfficeSiteRep {
    private MetOfficeDV metOfficeDV;

    @XmlElement(name = "DV")
    public MetOfficeDV getMetOfficeDV() {
        return metOfficeDV;
    }

    public void setMetOfficeDV(MetOfficeDV metOfficeDV) {
        this.metOfficeDV = metOfficeDV;
    }
}
