package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.RecipeMapper;
import io.eaterythem.eaterythem.model.Recipe;
import io.eaterythem.eaterythem.model.Tag;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.RecipeRepository;
import io.eaterythem.eaterythem.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final TagRepository tagRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDTO> dtos = new ArrayList<>();
        for (Recipe recipe : recipes) {
            dtos.add(recipeMapper.toDTO(recipe));
        }
        return dtos;
    }

    public List<RecipeDTO> getMeRecipes(UUID userId) {
        List<Recipe> recipes = recipeRepository.findByUserId(userId);
        List<RecipeDTO> dtos = new ArrayList<>();
        for (Recipe recipe : recipes) {
            dtos.add(recipeMapper.toDTO(recipe));
        }
        return dtos;
    }

    public RecipeDTO getRecipeById(UUID id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if (recipeOpt.isEmpty())
            throw new BadRequestException("Recipe not found");
        return recipeMapper.toDTO(recipeOpt.get());
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO, UUID userId) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        User user = new User();
        user.setId(userId);
        recipe.setUser(user);

        List<Tag> resolvedTags = new ArrayList<>();
        for (Tag t : recipe.getTags()) {
            Tag resolvedTag = tagRepository.findByName(t.getName())
                    .orElseGet(() -> tagRepository.save(t));
            resolvedTags.add(resolvedTag);
        }

        recipe.setTags(resolvedTags);
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDTO(recipe);
    }

    public RecipeDTO updateRecipe(UUID id, RecipeDTO recipeDTO, UUID userId) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Recipe not found"));

        if (recipe.getUser() == null || recipe.getUser().getId() != userId) {
            throw new UnauthorizedException("Only recipe creater can edit");
        }

        recipe.setName(recipeDTO.getName());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.setIngredients(recipeDTO.getIngredients());
        recipe.setMealType(recipeDTO.getMealType());

        List<Tag> resolvedTags = new ArrayList<>();
        for (Tag t : recipeMapper.toEntity(recipeDTO).getTags()) {
            Tag resolvedTag = tagRepository.findByName(t.getName())
                    .orElseGet(() -> tagRepository.save(t));
            resolvedTags.add(resolvedTag);
        }

        recipe.setTags(resolvedTags);

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDTO(recipe);
    }

    public void deleteRecipe(UUID id) {
        try {
            recipeRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Cannot delete recipe — it is used in meal plans or cycles.");
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Recipe not found.");
        }
    }

}