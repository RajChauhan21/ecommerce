package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/product")
public class UserProductController {

    @Autowired
    private UserProductService service;

    @GetMapping("cloth/{userId}/{clothId}")
    public ResponseEntity<?> orderOneCloth(@PathVariable int userId, @PathVariable int clothId){
        return new ResponseEntity<>(service.purchaseOneCloth(userId,clothId), HttpStatus.ACCEPTED);
    }

    @GetMapping("house/{userId}/{houseHold}")
    public ResponseEntity<?> purchaseOneHouseHoldItem(@PathVariable int userId, @PathVariable int houseHold){
        return new ResponseEntity<>(service.purchaseOneHouseHoldItem(userId,houseHold), HttpStatus.ACCEPTED);
    }

    @GetMapping("appliance/{userId}/{applianceId}")
    public ResponseEntity<?> purchaseOneAppliance(@PathVariable int userId, @PathVariable int applianceId){
        return new ResponseEntity<>(service.purchaseOneAppliance(userId,applianceId), HttpStatus.ACCEPTED);
    }

    @GetMapping("getCloth/{userId}")
    public ResponseEntity<?> getClothByUserId(@PathVariable int userId){
        return new ResponseEntity<>(service.getClothByUserId(userId),HttpStatus.ACCEPTED);
    }

    @GetMapping("getHouse/{userId}")
    public ResponseEntity<?> getHouseHoldsByUserId(@PathVariable int userId){
        return new ResponseEntity<>(service.getHouseHoldsByUserId(userId),HttpStatus.ACCEPTED);
    }

    @GetMapping("getAppliances/{userId}")
    public ResponseEntity<?> getAppliancesByUserId(@PathVariable int userId){
        return new ResponseEntity<>(service.getAppliancesByUserId(userId),HttpStatus.ACCEPTED);
    }

    @GetMapping("allProduct/{id}")
    public ResponseEntity<?> getAllProductsOfUser(@PathVariable int id){
        return service.getAllProductsOfUser(id);
    }

}
