package com.monolith.ecommerce.E_Commerce.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserAppliancePreference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonBackReference("user-app")
    private User user;

    private int applianceId;
}

