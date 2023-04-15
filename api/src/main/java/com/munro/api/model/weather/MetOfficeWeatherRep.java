package com.munro.api.model.weather;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class MetOfficeWeatherRep {
    private String V;
    private String W;
    private String S;
    private String Dm;
    private String FDm;
    private String PPd;

    @XmlAttribute(name = "V")
    public String getV() {
        return V;
    }

    public void setV(String v) {
        this.V = v;
    }

    @XmlAttribute(name = "W")
    public String getW() {
        return W;
    }

    public void setW(String w) {
        this.W = w;
    }

    @XmlAttribute(name = "S")
    public String getS() {
        return S;
    }

    public void setS(String s) {
        this.S = s;
    }

    @XmlAttribute(name = "Dm")
    public String getDm() {
        return Dm;
    }

    public void setDm(String dm) {
        this.Dm = dm;
    }

    @XmlAttribute(name = "FDm")
    public String getFDm() {
        return FDm;
    }

    public void setFDm(String FDm) {
        this.FDm = FDm;
    }

    @XmlAttribute(name = "PPd")
    public String getPPd() {
        return PPd;
    }

    public void setPPd(String PPd) {
        this.PPd = PPd;
    }
}
