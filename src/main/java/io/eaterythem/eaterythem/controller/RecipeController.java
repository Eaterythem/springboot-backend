package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/me")
    public List<RecipeDTO> getMeRecipes(@CurrentUser UserPrincipal user) {
        return recipeService.getMeRecipes(user.getUserId());
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Integer id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public RecipeDTO createRecipe(@RequestBody RecipeDTO recipeDTO, @CurrentUser UserPrincipal user) {
        return recipeService.createRecipe(recipeDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public RecipeDTO updateRecipe(@PathVariable Integer id, @RequestBody RecipeDTO recipeDTO, @CurrentUser UserPrincipal user) {
        return recipeService.updateRecipe(id, recipeDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        recipeService.deleteRecipe(id, user.getUserId());
    }
} 