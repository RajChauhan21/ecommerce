package com.monolith.ecommerce.E_Commerce.repository;



import com.monolith.ecommerce.E_Commerce.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HouseholdRepository extends JpaRepository<Household,Integer> {

    Optional<List<Household>> findByPriceLessThan(int price);
    Optional<List<Household>> findByPriceGreaterThan(int price);

    @Query(value = "SELECT * FROM household ORDER BY id LIMIT :record", nativeQuery = true)
    Optional<List<Household>> getAsPerRequired(int record);
}

