package com.monolith.ecommerce.E_Commerce.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uppers;

    private String lowers;

    private String shoes;

    private int price;

    private String UpperImage;

    private String LowerImage;

    private String ShoesImage;
}

