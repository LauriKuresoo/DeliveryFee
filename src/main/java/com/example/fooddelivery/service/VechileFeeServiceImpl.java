package com.example.fooddelivery.service;

import com.example.fooddelivery.entity.VechilePrices;
import com.example.fooddelivery.entity.Weather;
import com.example.fooddelivery.repository.VechileFeeRepository;
import com.example.fooddelivery.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VechileFeeServiceImpl implements VechileFeeService {

    @Autowired
    private VechileFeeRepository vechileFeeRepository;


    @Override
    public Iterable<VechilePrices> saveFees(List<VechilePrices> prices) {
        return vechileFeeRepository.saveAll(prices);
    }
}
