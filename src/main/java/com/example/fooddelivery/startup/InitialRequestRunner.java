package com.example.fooddelivery.startup;

import com.example.fooddelivery.FileManager;
import com.example.fooddelivery.controller.WeatherController;
import com.example.fooddelivery.domain.CitiesAndVechiles;
import com.example.fooddelivery.entity.VechilePrices;
import com.example.fooddelivery.service.VechileFeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitialRequestRunner implements ApplicationRunner {

    @Autowired
    WeatherController weatherController ;
    @Autowired
    VechileFeeServiceImpl vechileService;
    @Autowired
    FileManager fileManager;
    @Autowired
    CitiesAndVechiles citiesAndVechiles;


    HashSet<String> cityNames = new HashSet<>();
    HashSet<String> vechileNames = new HashSet<>();

    /*** A method for initializing databases when starting the application.
     *
     * @param args incoming application arguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<VechilePrices> fees = fileManager.readAllFromVechilesFile();

        for(VechilePrices vechile : fees){
            cityNames.add(vechile.getCityname());
            vechileNames.add(vechile.getVechile());
        }
        //Add the vechile names and city names to the citiesAndVechiles class so that the web application
        //  knows which cities are available.
        citiesAndVechiles.setCieties(cityNames);
        citiesAndVechiles.setVechiles(vechileNames);

        //Save the fees to a vechile fee database.
        vechileService.saveFees(fees);

        //Fetch the data from: https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php
        weatherController.fetchData();
        //Read the delivery prices for all vechiles in every city

    }

}
