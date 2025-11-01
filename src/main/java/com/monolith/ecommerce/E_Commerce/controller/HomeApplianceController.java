package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.entity.HomeAppliance;
import com.monolith.ecommerce.E_Commerce.service.HomeApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class HomeApplianceController {

    @Autowired
    private HomeApplianceService service;

    @GetMapping("price/below/{price}")
    public ResponseEntity<List<HomeAppliance>> findByPriceLessThan(@PathVariable int price){
        return service.findByPriceLessThan(price);
    }

    @GetMapping("price/above/{price}")
    public ResponseEntity<List<HomeAppliance>> findByPriceGreaterThan(@PathVariable int price){
        return service.findByPriceGreaterThan(price);
    }

    @GetMapping("getall")
    public ResponseEntity<List<HomeAppliance>> getAllAppliances(){
        return service.getAllAppliances();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<HomeAppliance> finById(@PathVariable int id){
        return service.finById(id);
    }

    @PostMapping("getByIds")
    public ResponseEntity<List<HomeAppliance>> finByMultipleIds(@RequestBody List<Integer> ids){
        return service.finByMultipleIds(ids);
    }
}
