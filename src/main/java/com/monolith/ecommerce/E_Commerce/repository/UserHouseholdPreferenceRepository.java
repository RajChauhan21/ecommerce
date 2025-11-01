package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.UserHouseholdPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserHouseholdPreferenceRepository extends JpaRepository<UserHouseholdPreference,Integer> {

    Optional<List<UserHouseholdPreference>> findByUserId(int id);
}
