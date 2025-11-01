package com.monolith.ecommerce.E_Commerce.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryDto {

    private int id;

    private int userId;

    private String productCategory;

    private String productName;

    private int price;

    private String image;

    private String upperImage;

    private String lowerImage;

    private String shoesImage;

    private int quantity;

    private float totalPrice;

    private Payment status;

    private String orderId;

    private String paymentId;

    private String paymentMethod;

    private String finalAmount;

    private LocalDateTime createdAt;

}
