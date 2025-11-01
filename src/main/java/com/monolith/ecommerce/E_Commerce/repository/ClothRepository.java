package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClothRepository extends JpaRepository<Clothes,Integer> {

    @Query(name = "SELECT * FROM clothes WHERE lowers LIKE :lowers")
    Optional<List<Clothes>> findByLowers(String lowers);

    @Query(name = "SELECT * FROM clothes WHERE lowers LIKE :uppers")
    Optional<List<Clothes>> findByUppers(String uppers);

    @Query(name = "SELECT * FROM clothes WHERE lowers LIKE :shoes")
    Optional<List<Clothes>> findByShoes(String shoes);

    Optional<List<Clothes>> findByPriceLessThan(int price);

    Optional<List<Clothes>> findByPriceGreaterThan(int price);

    @Query(value = "SELECT * FROM clothes ORDER BY id LIMIT :records", nativeQuery = true)
    Optional<List<Clothes>> getAsPerChoiceOnly(int records);
}

