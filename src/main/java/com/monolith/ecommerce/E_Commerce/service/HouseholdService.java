package com.monolith.ecommerce.E_Commerce.service;




import com.monolith.ecommerce.E_Commerce.entity.Household;
import com.monolith.ecommerce.E_Commerce.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository repository;

    public ResponseEntity<List<Household>> findByPriceLessThan(int price){
        return new ResponseEntity<>(repository.findByPriceLessThan(price).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Household>> findByPriceGreaterThan(int price){
        return new ResponseEntity<>(repository.findByPriceGreaterThan(price).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Household>> getAllItems(){
        return new ResponseEntity<>(repository.getAsPerRequired(50).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Household> findById(int id){
        return new ResponseEntity<>(repository.findById(id).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Household>> findByMultipleIds(List<Integer> ids){
        List<Household> list = new ArrayList<>();
        for(int id : ids){
            Household household = repository.findById(id).get();
            list.add(household);
        }
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }


}

