package com.monolith.ecommerce.E_Commerce.service;



import com.monolith.ecommerce.E_Commerce.entity.HomeAppliance;
import com.monolith.ecommerce.E_Commerce.repository.HomeApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeApplianceService {

    @Autowired
    private HomeApplianceRepository repository;

    public ResponseEntity<List<HomeAppliance>> findByPriceLessThan(int price){
        return new ResponseEntity<>(repository.findByPriceLessThan(price).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<HomeAppliance>> findByPriceGreaterThan(int price){
        return new ResponseEntity<>(repository.findByPriceGreaterThan(price).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<HomeAppliance>> getAllAppliances(){
        return new ResponseEntity<>(repository.getAsPerChoiceOnly(50).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<HomeAppliance> finById(int id){
        return new ResponseEntity<>(repository.findById(id).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<HomeAppliance>> finByMultipleIds(List<Integer> ids){

        List<HomeAppliance> list = new ArrayList<>();
        for(int id : ids){
            HomeAppliance homeAppliance = repository.findById(id).get();
            list.add(homeAppliance);
        }
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

}


