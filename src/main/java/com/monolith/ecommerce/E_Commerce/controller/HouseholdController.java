package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.entity.Household;
import com.monolith.ecommerce.E_Commerce.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("household")
public class HouseholdController {

    @Autowired
    private HouseholdService service;

    @GetMapping("price/below/{price}")
    public ResponseEntity<List<Household>> findByPriceLessThan(@PathVariable int price){
        return service.findByPriceLessThan(price);
    }

    @GetMapping("price/above/{price}")
    public ResponseEntity<List<Household>> findByPriceGreaterThan(@PathVariable int price){
        return service.findByPriceGreaterThan(price);
    }

    @GetMapping("getall")
    public ResponseEntity<List<Household>> getAllItems(){
        return service.getAllItems();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Household> findById(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping("getByIds")
    public ResponseEntity<List<Household>> findByMultipleIds(@RequestBody List<Integer> ids){
        return service.findByMultipleIds(ids);
    }

}

