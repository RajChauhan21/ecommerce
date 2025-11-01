package com.monolith.ecommerce.E_Commerce.DTO;

import lombok.Data;

@Data
public class UpdateCartDTO {

    private int id;

    private String productCategory;

    private String productName;

    private int price;

    private String image;

    private String upperImage;

    private String lowerImage;

    private String shoesImage;

    private int quantity;

    private float totalPrice;
}
