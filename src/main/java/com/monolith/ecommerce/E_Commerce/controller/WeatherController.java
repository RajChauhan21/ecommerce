package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("get/{name}")
    public ResponseEntity<?> getLocationData(@PathVariable String name){
        return service.GetCurrentWeather(name);
    }

}
