package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DTO.UserDto;
import com.monolith.ecommerce.E_Commerce.DTO.UserProductDto;
import com.monolith.ecommerce.E_Commerce.entity.*;
import com.monolith.ecommerce.E_Commerce.repository.*;
import com.monolith.ecommerce.E_Commerce.responses.ApplianceResponse;
import com.monolith.ecommerce.E_Commerce.responses.ClothResponse;
import com.monolith.ecommerce.E_Commerce.responses.HouseHoldResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProductService {

    @Autowired
    private UserHouseholdPreferenceRepository householdPreferenceRepository;

    @Autowired
    private UserClothingPreferenceRepository clothingPreferenceRepository;

    @Autowired
    private UserAppliancePreferenceRepository appliancePreferenceRepository;

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private HomeApplianceRepository homeApplianceRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> purchaseOneCloth(int userId, int clothId){
        User user = userRepository.findById(userId).get();
        UserClothingPreference clothingPreference = new UserClothingPreference();
        clothingPreference.setClothId(clothId);
        clothingPreference.setUser(user);
        clothingPreferenceRepository.save(clothingPreference);
        return new ResponseEntity<>("Order updated successfully", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> purchaseOneHouseHoldItem(int userId, int houseHoldId){
        User user = userRepository.findById(userId).get();
        UserHouseholdPreference householdPreference = new UserHouseholdPreference();
        householdPreference.setUser(user);
        householdPreference.setHouseHoldId(houseHoldId);
        householdPreferenceRepository.save(householdPreference);

        return new ResponseEntity<>("Order updated successfully", HttpStatus.ACCEPTED);
    }
    public ResponseEntity<?> purchaseOneAppliance(int userId, int applianceId){
        User user = userRepository.findById(userId).get();
        UserAppliancePreference userAppliancePreference = new UserAppliancePreference();
        userAppliancePreference.setUser(user);
        userAppliancePreference.setApplianceId(applianceId);
        appliancePreferenceRepository.save(userAppliancePreference);

        return new ResponseEntity<>("Order updated successfully", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<ClothResponse>> getClothByUserId(int userId){
        List<UserClothingPreference> clothingPreferenceList = clothingPreferenceRepository.findByUserId(userId).get();
        List<ClothResponse> clothResponses = new ArrayList<>();
        ClothResponse clothResponse = new ClothResponse();

        for(UserClothingPreference preference : clothingPreferenceList){
            Optional<Clothes> byId = clothRepository.findById(preference.getClothId());
            Clothes clothes = byId.get();
            clothResponse.setId(clothes.getId());
            clothResponse.setLowers(clothes.getLowers());
            clothResponse.setUppers(clothes.getUppers());
            clothResponse.setShoes(clothes.getShoes());
            clothResponse.setPrice(clothes.getPrice());
            clothResponse.setLowerImage(clothes.getLowerImage());
            clothResponse.setUpperImage(clothes.getUpperImage());
            clothResponse.setShoesImage(clothes.getShoesImage());
        }
        clothResponses.add(clothResponse);
        return new ResponseEntity<>(clothResponses, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<HouseHoldResponse>> getHouseHoldsByUserId(int userId){
        List<UserHouseholdPreference> householdPreferences = householdPreferenceRepository.findByUserId(userId).get();
        HouseHoldResponse houseHoldResponse = new HouseHoldResponse();
        List<HouseHoldResponse> list = new ArrayList<>();
        for(UserHouseholdPreference preference : householdPreferences){
            Household household = householdRepository.findById(preference.getId()).get();
            houseHoldResponse.setId(household.getId());
            houseHoldResponse.setPrice(household.getPrice());
            houseHoldResponse.setItem(household.getItem());
            houseHoldResponse.setImage(household.getImage());
        }
        list.add(houseHoldResponse);
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<ApplianceResponse>> getAppliancesByUserId(int userId){
        List<UserAppliancePreference> byUserId = appliancePreferenceRepository.findByUserId(userId).get();
        ApplianceResponse applianceResponse = new ApplianceResponse();
        List<ApplianceResponse> list = new ArrayList<>();
        for (UserAppliancePreference preference : byUserId){
            HomeAppliance appliance = homeApplianceRepository.findById(preference.getId()).get();
            applianceResponse.setId(appliance.getId());
            applianceResponse.setAppliance(appliance.getAppliance());
            applianceResponse.setPrice(appliance.getPrice());
            applianceResponse.setImage(appliance.getImage());
        }
        list.add(applianceResponse);
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getAllProductsOfUser(int userId){
        List<ClothResponse> clothResponses = getClothByUserId(userId).getBody();
        List<HouseHoldResponse> houseHoldResponses = getHouseHoldsByUserId(userId).getBody();
        List<ApplianceResponse> applianceResponses = getAppliancesByUserId(userId).getBody();

        User user = userRepository.findById(userId).get();
        UserDto userDto = new UserDto();
        UserProductDto userProductDto = new UserProductDto();
        userDto.setName(user.getName());
        userProductDto.setName(user.getName());
//        userProductDto.set
//        userDto.setClothingPreferences(clothResponses);
//        userDto.setHouseholdPreferences(houseHoldResponses);
//        userDto.setAppliancePreferences(applianceResponses);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }


}
