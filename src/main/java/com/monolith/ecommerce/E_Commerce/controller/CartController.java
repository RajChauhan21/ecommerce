package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.DRO.CartRequest;
import com.monolith.ecommerce.E_Commerce.DTO.UpdateCartDTO;
import com.monolith.ecommerce.E_Commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    private ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest){
        return cartService.addToCart(cartRequest);
    }

    @PostMapping("update")
    private ResponseEntity<?> updateCart(@RequestBody List<UpdateCartDTO> updateCartDTO){
        return cartService.updateCart(updateCartDTO);
    }

    @GetMapping("getAll/{userId}")
    public ResponseEntity<?> getCartItemsOfUser(@PathVariable int userId){
        return cartService.getCartItemsOfUser(userId);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable int id){
        return cartService.removeFromCart(id);
    }
}
