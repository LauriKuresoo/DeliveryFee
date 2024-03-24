package com.example.fooddelivery.domain;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CitiesAndVechiles {
    HashSet<String> cieties = new HashSet<>();
    HashSet<String> vechiles = new HashSet<>();

    public void setCieties(HashSet<String> cieties) {
        this.cieties = cieties;
    }

    public HashSet<String> getCities() {
        return cieties;
    }

    public void setVechiles(HashSet<String> vechileNames) {
        this.vechiles = vechileNames;
    }

    public HashSet<String> getVechiles() {
        return vechiles;
    }
}
