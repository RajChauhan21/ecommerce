package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.entity.Clothes;
import com.monolith.ecommerce.E_Commerce.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("cloth")
public class ClothController {

    @Autowired
    private ClothService clothService;

    @GetMapping("get")
    public ResponseEntity<List<Clothes>> findAll(){
        return clothService.getAllClothes();
    }

    @GetMapping("uppers/{uppers}")
    public ResponseEntity<List<Clothes>> findByUppers(@PathVariable String uppers){
        return clothService.findByUppers(uppers);
    }

    @GetMapping("lowers/{lowers}")
    public ResponseEntity<List<Clothes>> findByLowers(@PathVariable String lowers){
        return clothService.findByLowers(lowers);
    }

    @GetMapping("shoes/{shoes}")
    public ResponseEntity<List<Clothes>> findByShoes(@PathVariable String shoes){
        return clothService.findByShoes(shoes);
    }

    @GetMapping("below/{price}")
    public ResponseEntity<List<Clothes>> filterByPriceBelow(@PathVariable int price){
        return clothService.findByPriceLessThan(price);
    }

    @GetMapping("above/{price}")
    public ResponseEntity<List<Clothes>> filterByPriceAbove(@PathVariable int price){
        return clothService.findByPriceGreaterThan(price);
    }

    @GetMapping("by/{id}")
    public ResponseEntity<Clothes> findById(@PathVariable int id){
        return clothService.findById(id);
    }

    @PostMapping("getByIds")
    public ResponseEntity<List<Clothes>> findByMultipleIds(@RequestBody List<Integer> ids){
        return clothService.findByMultipleIds(ids);
    }
}

