package io.eaterythem.eaterythem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import io.eaterythem.eaterythem.model.CycleRecipe;


public interface CycleRecipeRepository extends JpaRepository<CycleRecipe, Integer>{
    
}
