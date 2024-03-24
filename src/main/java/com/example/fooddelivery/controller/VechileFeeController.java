package com.example.fooddelivery.controller;

import com.example.fooddelivery.entity.VechilePrices;
import com.example.fooddelivery.repository.VechileFeeRepository;
import com.example.fooddelivery.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VechileFeeController {

    @Autowired
    VechileFeeRepository vechileFeeRepository;

    @GetMapping("/vechiles/{cityName}/{vechile}")
    public VechilePrices fetchFee(@PathVariable String cityName, String vechile){
        return vechileFeeRepository.findByVechileAndCityname(vechile, cityName);
    }


}
