package com.monolith.ecommerce.E_Commerce.service;



import com.monolith.ecommerce.E_Commerce.entity.Clothes;
import com.monolith.ecommerce.E_Commerce.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothService {

    @Autowired
    private ClothRepository clothRepository;

    public ResponseEntity<List<Clothes>> getAllClothes(){
        return new ResponseEntity<>(clothRepository.getAsPerChoiceOnly(50).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Clothes>> findByUppers(String uppers){
        return new ResponseEntity<>(clothRepository.findByUppers(uppers).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Clothes>> findByLowers(String lowers){
        return new ResponseEntity<>(clothRepository.findByLowers(lowers).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Clothes>> findByShoes(String shoes){
        return new ResponseEntity<>(clothRepository.findByShoes(shoes).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Clothes>> findByPriceLessThan(int price){
        return new ResponseEntity<>(clothRepository.findByPriceLessThan(price).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Clothes>> findByPriceGreaterThan(int price){
        return new ResponseEntity<>(clothRepository.findByPriceGreaterThan(price).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Clothes> findById(int id){
        return new ResponseEntity<>(clothRepository.findById(id).get(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Clothes>> findByMultipleIds(List<Integer> ids){
        List<Clothes> list = new ArrayList<>();
        for(int id : ids){
            Clothes clothes = clothRepository.findById(id).get();
            list.add(clothes);
        }
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

}

