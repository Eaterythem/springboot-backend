package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {

    List<MealPlan> getByUserId(Integer userId);
} 