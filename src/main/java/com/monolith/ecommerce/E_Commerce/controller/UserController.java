package com.monolith.ecommerce.E_Commerce.controller;


import com.monolith.ecommerce.E_Commerce.DTO.LoginRequest;
import com.monolith.ecommerce.E_Commerce.DTO.UserProductDto;
import com.monolith.ecommerce.E_Commerce.DTO.UserProfileDto;
import com.monolith.ecommerce.E_Commerce.entity.User;
import com.monolith.ecommerce.E_Commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody User user){
        return new ResponseEntity<>(service.register(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("signIn")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest user){
        return new ResponseEntity<>(service.signIn(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<UserProductDto> getAllUsers(@PathVariable int id){
        return service.getUserById(id);
    }

    @PostMapping("update/user")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileDto profileDto){
        return service.updateProfile(profileDto);
    }

    @GetMapping("getUserProfile/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable int id){
        return service.getUserProfileById(id);
    }

    @PostMapping("upload/image/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable int id, @RequestParam("file")MultipartFile file) throws IOException {
        return service.uploadUserProfileImage(id,file);
    }
}
