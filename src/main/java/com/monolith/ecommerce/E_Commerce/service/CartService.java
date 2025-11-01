package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DRO.CartRequest;
import com.monolith.ecommerce.E_Commerce.DTO.UpdateCartDTO;
import com.monolith.ecommerce.E_Commerce.entity.*;
import com.monolith.ecommerce.E_Commerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private HomeApplianceRepository homeApplianceRepository;


    public ResponseEntity<?> addToCart(CartRequest cartRequest){
        Cart cart = new Cart();;
        User user = userRepository.findById(cartRequest.getUserId()).get();
       //new cart
            if (Objects.equals(cartRequest.getProductCategory(), "CLOTHES")) {
                Clothes clothes = clothRepository.findById(cartRequest.getProductId()).get();
                cart.setUpperImage(clothes.getUpperImage());
                cart.setLowerImage(clothes.getLowerImage());
                cart.setShoesImage(clothes.getShoesImage());
                cart.setUser(user);
                String fullName = clothes.getUppers() + " " + clothes.getLowers() + " " + clothes.getShoes();
                cart.setProductName(fullName);
                cart.setPrice(clothes.getPrice());
                cart.setProductCategory(cartRequest.getProductCategory());
                cart.setTotalPrice((float)clothes.getPrice());
                cart.setQuantity(cartRequest.getQuantity());
                repository.save(cart);

                return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
            }

            if (Objects.equals(cartRequest.getProductCategory(), "HOUSEHOLD")) {
                Household houseHold = householdRepository.findById(cartRequest.getProductId()).get();
                cart.setUser(user);
                cart.setImage(houseHold.getImage());
                cart.setPrice(houseHold.getPrice());
                cart.setProductName(houseHold.getItem());
                cart.setProductCategory(cartRequest.getProductCategory());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setTotalPrice((float)houseHold.getPrice());
                repository.save(cart);

                return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
            }

            if (Objects.equals(cartRequest.getProductCategory(), "APPLIANCE")) {
                HomeAppliance appliance = homeApplianceRepository.findById(cartRequest.getProductId()).get();
                cart.setUser(user);
                cart.setImage(appliance.getImage());
                cart.setPrice(appliance.getPrice());
                cart.setProductName(appliance.getAppliance());
                cart.setProductCategory(cartRequest.getProductCategory());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setTotalPrice((float) appliance.getPrice());
                repository.save(cart);
                return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
            }

        return new ResponseEntity<>("INVALID PRODUCT TYPE",HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<?> updateCart(List<UpdateCartDTO> cartDTO){
        List<Cart> carts = new ArrayList<>();
        for (UpdateCartDTO cart : cartDTO){
            Cart c = repository.findById(cart.getId()).get();
            c.setQuantity(cart.getQuantity());
            c.setTotalPrice(cart.getTotalPrice());
            carts.add(c);
            repository.save(c);
        }

        return new ResponseEntity<>(carts,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getCartItemsOfUser(int userId){
        List<Cart> cartItems = repository.findByUserId(userId);
        return new ResponseEntity<>(cartItems,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> removeFromCart(int productId){
        Cart cart = repository.findById(productId).get();
        repository.deleteById(productId);
        return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
    }
}
