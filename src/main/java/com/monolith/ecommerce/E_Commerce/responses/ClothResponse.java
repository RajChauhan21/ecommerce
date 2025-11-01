package com.monolith.ecommerce.E_Commerce.responses;

import lombok.Data;

@Data
public class ClothResponse {
    private int id;

    private String uppers;

    private String lowers;

    private String shoes;

    private int price;

    private String upperImage;

    private String lowerImage;

    private String shoesImage;
}
