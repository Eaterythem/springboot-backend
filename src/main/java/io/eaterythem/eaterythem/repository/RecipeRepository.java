package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Recipe;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByUserId(Integer userId);
} 