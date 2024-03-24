package com.example.fooddelivery;

import com.example.fooddelivery.controller.VechileFeeController;
import com.example.fooddelivery.controller.WeatherController;
import com.example.fooddelivery.entity.VechilePrices;
import com.example.fooddelivery.entity.Weather;
import com.example.fooddelivery.exceptions.DeliveryFeeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
@Setter
public class DeliveryFeeCalculator {

    @Autowired
    private VechileFeeController VechileFeeController;

    @Autowired
    private WeatherController weatherController;


    /*** Main method for calculating the delivery fee total sum.
     *
     * @param city searched city
     * @param vechile searched vechile
     * @return delivery fee total
     */
    public double calculateDeliveryFee(String city, String vechile) {
        //get weather data and vechile pricing data
        Weather weather = weatherController.fetchWeather(city);
        VechilePrices vechileObject = VechileFeeController.fetchFee(city, vechile);

        //initiate total fee with the vechile fee
        double totalFee = vechileObject.getPrice();

        double temperature = weather.getAirTemperature();
        if (temperature < -10.0 ) totalFee += 1.0;
        else if (temperature >= -10.0 && temperature < 0) totalFee += 0.5;

        double windSpeed = weather.getWindSpeed();
        if(vechile.equals("Bike")){
            if(windSpeed > 10.0 && windSpeed <= 20.0) totalFee += 0.5;
            else if (windSpeed > 20.0) throw new DeliveryFeeException("Too windy for bike delivery");
        }

        String weatherPhenomenon = weather.getWheaterPhenomenon();
        if(vechile.equals("Scooter") || vechile.equals("Bike")){
            if (weatherPhenomenon.toLowerCase().contains("snow") || weatherPhenomenon.toLowerCase().contains("sleet")) totalFee += 1.0;
            else if (weatherPhenomenon.toLowerCase().contains("rain")) totalFee += 0.5;
            else if (weatherPhenomenon.toLowerCase().contains("glaze") || weatherPhenomenon.toLowerCase().contains("hail") || weatherPhenomenon.toLowerCase().contains("thunder")) {
                throw new DeliveryFeeException("Weather phenomenon is too risky for selected vechile!");
            }

        }
        return totalFee;
    }
}
