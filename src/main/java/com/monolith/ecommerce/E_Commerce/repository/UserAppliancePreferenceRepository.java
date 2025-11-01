package com.monolith.ecommerce.E_Commerce.repository;

import com.monolith.ecommerce.E_Commerce.entity.UserAppliancePreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAppliancePreferenceRepository extends JpaRepository<UserAppliancePreference,Integer> {

    Optional<List<UserAppliancePreference>> findByUserId(int id);
}
