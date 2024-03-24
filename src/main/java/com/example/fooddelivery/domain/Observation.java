package com.example.fooddelivery.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name="observations")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Observation {

    @XmlElement(name = "station")
    private List<Station> stations;


    public void setStations(List<Station> stations){
        this.stations = stations;
    }

    @XmlAttribute(name="timestamp")
    private long timestamp;

    @Override
    public String toString() {
        return "\ntimestamp: " + timestamp
                + stations.toString();
    }
}
