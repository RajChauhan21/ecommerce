package com.monolith.ecommerce.E_Commerce.DRO;

import lombok.Data;

@Data
public class CartRequest {

    private int userId;

    private int productId;

    private String productCategory;

    private int quantity;
}
