package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.UserClothingPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserClothingPreferenceRepository extends JpaRepository<UserClothingPreference,Integer> {

    Optional<List<UserClothingPreference>> findByUserId(int id);
}
