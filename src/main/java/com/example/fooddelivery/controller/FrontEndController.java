package com.example.fooddelivery.controller;

import com.example.fooddelivery.DeliveryFeeCalculator;
import com.example.fooddelivery.domain.CitiesAndVechiles;
import com.example.fooddelivery.exceptions.DeliveryFeeException;
import com.example.fooddelivery.exceptions.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontEndController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    CitiesAndVechiles citiesAndVechiles;
    @Autowired
    DeliveryFeeCalculator deliveryFeeCalculator;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("cityNames", citiesAndVechiles.getCities());
        model.addAttribute("vechileNames", citiesAndVechiles.getVechiles());
        model.addAttribute("fee", "");
        return "home";
    }

    @PostMapping("/submit")
    public String submitForm(@RequestParam("cityName") String cityName, @RequestParam("vehicleType") String vehicleType, Model model){
        try{
            double fee = deliveryFeeCalculator.calculateDeliveryFee(cityName, vehicleType);
            model.addAttribute("fee", Double.toString(fee));
        }
        catch (DeliveryFeeException e){
            model.addAttribute("fee", e.getMessage());
        }
        catch (NoDataException e){
            model.addAttribute("fee", e.getMessage());
        }

        return "result";
    }



}
