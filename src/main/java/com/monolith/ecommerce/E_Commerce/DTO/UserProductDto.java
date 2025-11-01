package com.monolith.ecommerce.E_Commerce.DTO;


import com.monolith.ecommerce.E_Commerce.entity.Cart;
import com.monolith.ecommerce.E_Commerce.entity.UserAppliancePreference;
import com.monolith.ecommerce.E_Commerce.entity.UserClothingPreference;
import com.monolith.ecommerce.E_Commerce.entity.UserHouseholdPreference;
import com.monolith.ecommerce.E_Commerce.responses.ApplianceResponse;
import com.monolith.ecommerce.E_Commerce.responses.ClothResponse;
import com.monolith.ecommerce.E_Commerce.responses.HouseHoldResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserProductDto {

    private int id;
    private String email;
    private String name;
    private List<ClothResponse> clothingPreferences = new ArrayList<>();
    private List<HouseHoldResponse> householdPreferences = new ArrayList<>();
    private List<ApplianceResponse> appliancePreferences = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
}
