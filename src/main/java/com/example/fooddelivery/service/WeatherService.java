package com.example.fooddelivery.service;

import com.example.fooddelivery.entity.Weather;

import java.util.List;

public interface WeatherService {
    Weather saveWeather(Weather weather);

    Iterable<Weather> saveWeather(List<Weather> weathers);

    List<Weather> fetchWeatherList();

}
