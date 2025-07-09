package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, UUID> {
    // Additional query methods if needed
} 