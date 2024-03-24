package com.example.fooddelivery.controller;

import com.example.fooddelivery.exceptions.NoDataException;
import com.example.fooddelivery.repository.WeatherRepository;
import jakarta.xml.bind.*;

import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import com.example.fooddelivery.domain.Observation;
import com.example.fooddelivery.entity.Weather;
import com.example.fooddelivery.service.WeatherMapper;
import com.example.fooddelivery.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;

/***
 * Main controller class for fetching data from the "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php" website
 * and to fetch data from our own weather database.
 */
@RestController
public class WeatherController {


    @Autowired
    private WeatherService weatherService;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    private WeatherMapper weatherMapper;

    /*** REST template method to fetch data from the specified URL.
     *
     * @throws JAXBException
     */
    @Scheduled(cron = "0 11 * * * *")
    @GetMapping("/fetchData")
    public void fetchData() throws JAXBException{
        String URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        //Get the XML as a String
        String response = restTemplate.getForObject(URL, String.class);


        JAXBContext jaxbContext = JAXBContext.newInstance(Observation.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try{
            //Parse the xml into a Observation object
            Observation obs = (Observation) unmarshaller.unmarshal(new StringReader(response));
            //Map the Observation data to weather entity
            weatherService.saveWeather(weatherMapper.mapObservationsToWeatherList(obs));
        }catch (JAXBException e){
            throw new NoDataException("There was nothing to read from the URL: " + URL + "\n"
            + "response: " + response);
        }

    }

    /*** fetch latest station observation data from the Weather database.
     *
     * @param stationName station name to be fetched
     * @return latest weather reprot in the station
     */
    @GetMapping("/weather/{stationName}")
    public Weather fetchWeather(@PathVariable String stationName){
        try {
            Weather weather = weatherRepository.findLatestByStationName(stationName);
            if(weather != null) return weather;
            throw new NoDataException("No data was found for these parameters");
        }catch (IllegalArgumentException e){
            throw new  NoDataException("Database is empty!");
        }

    }
}
