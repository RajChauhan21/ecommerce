package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByUserId(int userId);
}
