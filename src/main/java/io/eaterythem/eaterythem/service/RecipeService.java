package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecipeService {
    public List<RecipeDTO> getAllRecipes() { return new ArrayList<>(); }
    public RecipeDTO getRecipeById(UUID id) { return null; }
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) { return null; }
    public RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO) { return null; }
    public void deleteRecipe(UUID id) {}
} 