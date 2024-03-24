package com.example.fooddelivery.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name="station")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Station {
    @XmlElement(name = "name")
    private String station_name;
    @XmlElement(name = "wmocode", nillable = true)
    private Integer wmo_code;
    @XmlElement(name = "airtemperature", nillable = true)
    private Double air_temperature;
    @XmlElement(name = "windspeed", nillable = true)
    private Double wind_speed;
    @XmlElement(name = "phenomenon", nillable = true)
    private String wheater_phenomenon;

    @Override
    public String toString() {
        return "\n\tstation_name: " + station_name + "\n\tair_temperature: " +  air_temperature +"   pheonomenon: " + wheater_phenomenon;
    }
}
