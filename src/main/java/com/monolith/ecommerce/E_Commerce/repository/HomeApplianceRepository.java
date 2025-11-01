package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.HomeAppliance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface HomeApplianceRepository extends JpaRepository<HomeAppliance,Integer> {

    Optional<List<HomeAppliance>> findByPriceLessThan(int price);
    Optional<List<HomeAppliance>> findByPriceGreaterThan(int price);

    @Query(value = "SELECT * FROM home_appliance ORDER BY id LIMIT :records", nativeQuery = true)
    Optional<List<HomeAppliance>> getAsPerChoiceOnly(int records);

}

