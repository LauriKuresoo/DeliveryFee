package com.example.fooddelivery.entity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@IdClass(WeatherId.class)
@Table(name= "weather")

/***
 * A entity class for weather database
 */

public class Weather {
    @Id
    @Column(nullable = false)
    private String stationName;

    private Integer wmoCode;

    private Double airTemperature;

    private Double windSpeed;

    private String wheaterPhenomenon;
    @Id
    private Timestamp timestamp;
}
