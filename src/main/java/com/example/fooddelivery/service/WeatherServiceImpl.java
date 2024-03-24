package com.example.fooddelivery.service;

import com.example.fooddelivery.entity.Weather;
import com.example.fooddelivery.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public Iterable<Weather> saveWeather(List<Weather> weathers) {
        return weatherRepository.saveAll(weathers);
    }

    @Override
    public List<Weather> fetchWeatherList() {

        return (List<Weather>)weatherRepository.findAll();
    }

}