package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DTO.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${WEATHER_API}")
   private String WeatherApiKey;
    private String url = "http://api.weatherstack.com/current?access_key=API_KEY&query=locName";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> GetCurrentWeather(String location){
        String FinalUrl = url.replace("API_KEY",WeatherApiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(FinalUrl.replace("locName", location), HttpMethod.GET, null, WeatherResponse.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.ACCEPTED);

    }
}
