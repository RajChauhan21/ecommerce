package com.monolith.ecommerce.E_Commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JsonBackReference("user-carts")
    private User user;
}

