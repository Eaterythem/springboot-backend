package io.eaterythem.eaterythem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.eaterythem.eaterythem.model.MealCycleRecipe;


public interface MealCycleRecipeRepository extends JpaRepository<MealCycleRecipe, UUID>{
    
}
