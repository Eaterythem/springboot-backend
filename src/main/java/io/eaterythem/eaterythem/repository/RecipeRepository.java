package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Recipe;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    // Additional query methods if needed
} 