package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Additional query methods if needed
} 