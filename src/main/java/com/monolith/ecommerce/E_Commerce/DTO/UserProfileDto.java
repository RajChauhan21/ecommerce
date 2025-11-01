package com.monolith.ecommerce.E_Commerce.DTO;

import lombok.Data;

@Data
public class UserProfileDto {

    private int id;

    private String name;

    private String email;

    private String address;

    private String phone;

    private String avatar;

    private String country;

    private String city;

    private int postalCode;

    private String token;
}
