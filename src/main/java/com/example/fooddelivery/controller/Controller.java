package com.example.fooddelivery.controller;

import com.example.fooddelivery.DeliveryFeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    DeliveryFeeCalculator deliveryFeeCalculator;

    @GetMapping("/{cityName}/{vechile}")
    public double calculateFee(@PathVariable String cityName,@PathVariable String vechile){
            double fee = deliveryFeeCalculator.calculateDeliveryFee(cityName, vechile);
            return fee;
    }

}
