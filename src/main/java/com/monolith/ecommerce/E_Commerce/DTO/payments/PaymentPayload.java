package com.monolith.ecommerce.E_Commerce.DTO.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPayload {
    public String entity;
    public String account_id;
    public String event;
    public ArrayList<String> contains;
    public Payload payload;
    public int created_at;
}
