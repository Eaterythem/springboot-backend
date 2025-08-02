package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, UUID> {

    List<MealPlan> getByUserId(UUID userId);
} 