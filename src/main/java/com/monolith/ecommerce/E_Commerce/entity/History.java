package com.monolith.ecommerce.E_Commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.monolith.ecommerce.E_Commerce.DTO.Payment;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class History {

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

    @Enumerated(EnumType.STRING)
    private Payment status;

    private String orderId;

    private String paymentId;

    private String paymentMethod;

    private String finalAmount;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference("user-history")
    private User user;
}
