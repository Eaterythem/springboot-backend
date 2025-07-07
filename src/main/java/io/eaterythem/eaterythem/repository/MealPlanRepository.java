package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    // Additional query methods if needed
} 