package com.example.fooddelivery.service;


import com.example.fooddelivery.entity.VechilePrices;
import com.example.fooddelivery.entity.Weather;

import java.util.List;

public interface VechileFeeService {
    Iterable<VechilePrices> saveFees(List<VechilePrices> vechilePrices);



}
