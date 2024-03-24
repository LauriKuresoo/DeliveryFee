package com.example.fooddelivery.service;

import com.example.fooddelivery.domain.CitiesAndVechiles;
import com.example.fooddelivery.domain.Observation;
import com.example.fooddelivery.domain.Station;
import com.example.fooddelivery.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;


/***
 * A mapper class for mapping the objects Observation and Stations parsed from XML formatted weather data into
 * a weather entity class.
 *
 * Observation.class, Stations.class  -> Weather.class
 */
@Component
public class WeatherMapper {

    private HashSet<String> locations = new HashSet<>();

    @Autowired
    CitiesAndVechiles citiesAndVechiles;


    /*** Maps observation object to a weather entity
     *
     * @param observation Observation to be mapped into a list of Weather entities
     * @return list of weather entities
     */
    public List<Weather> mapObservationsToWeatherList(Observation observation) {
        HashSet<String> cities = citiesAndVechiles.getCities();
        List<Weather> weatherList = new ArrayList<>();
        List<Station> stations = observation.getStations();

        if (stations != null) {
            for (Station station : stations) {
                if(cities.contains(station.getStation_name())) {
                    Weather weather = mapStationToWeather(station, observation.getTimestamp());
                    weatherList.add(weather);
                }
            }
        }
        return weatherList;
    }


    /***
     *
     * @param station Station to be mapped to a weather entity
     * @param timestamp Observation timestamp
     * @return weather entity
     */
    public Weather mapStationToWeather(Station station, long timestamp) {
        Weather weather = new Weather();
        weather.setStationName(station.getStation_name());
        weather.setWmoCode(station.getWmo_code());
        weather.setAirTemperature(station.getAir_temperature());
        weather.setWindSpeed(station.getWind_speed());
        weather.setWheaterPhenomenon(station.getWheater_phenomenon());
        weather.setTimestamp(new Timestamp(timestamp*1000));
        return weather;
    }
}
