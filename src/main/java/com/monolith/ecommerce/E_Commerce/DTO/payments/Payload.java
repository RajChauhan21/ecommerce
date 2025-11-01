package com.monolith.ecommerce.E_Commerce.DTO.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    public Payment payment;
    public Order order;
}
