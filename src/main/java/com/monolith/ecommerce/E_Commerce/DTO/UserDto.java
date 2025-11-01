package com.monolith.ecommerce.E_Commerce.DTO;


import com.monolith.ecommerce.E_Commerce.entity.Cart;
import com.monolith.ecommerce.E_Commerce.responses.ApplianceResponse;
import com.monolith.ecommerce.E_Commerce.responses.ClothResponse;
import com.monolith.ecommerce.E_Commerce.responses.HouseHoldResponse;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String token;
//    private List<Cart> cartList  = new ArrayList<>();
//    private List<ClothResponse> clothingPreferences = new ArrayList<>();
//    private List<HouseHoldResponse> householdPreferences = new ArrayList<>();
//    private List<ApplianceResponse> appliancePreferences = new ArrayList<>();




}
