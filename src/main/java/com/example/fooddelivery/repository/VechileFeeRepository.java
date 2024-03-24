package com.example.fooddelivery.repository;

import com.example.fooddelivery.entity.VechilePrices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VechileFeeRepository extends CrudRepository<VechilePrices, Long> {
    VechilePrices findByVechileAndCityname(String vechileName, String CityName);
}
