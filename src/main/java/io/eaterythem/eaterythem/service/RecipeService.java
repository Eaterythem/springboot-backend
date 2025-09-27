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
import io.eaterythem.eaterythem.tools.ObjectMerger;
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
        List<RecipeDTO> recipeDTOs = recipeMapper.toDTO(recipes);
        return recipeDTOs;
    }

    public List<RecipeDTO> getMeRecipes(Integer userId) {
        List<Recipe> recipes = recipeRepository.findByUserId(userId);
        List<RecipeDTO> dtos = new ArrayList<>();
        for (Recipe recipe : recipes) {
            dtos.add(recipeMapper.toDTO(recipe));
        }
        return dtos;
    }

    public RecipeDTO getRecipeById(Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Recipe not found"));

        return recipeMapper.toDTO(recipe);
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO, Integer userId) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        User user = new User();
        user.setId(userId);
        recipe.setUser(user);

        List<Tag> resolvedTags = new ArrayList<>();
        for (Tag t : recipe.getTags()) {
            t.setName(t.getName().toLowerCase().strip());
            Tag resolvedTag = tagRepository.findByName(t.getName())
                    .orElseGet(() -> tagRepository.save(t));
            resolvedTags.add(resolvedTag);
        }

        recipe.setTags(resolvedTags);
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDTO(recipe);
    }

    public RecipeDTO updateRecipe(Integer id, RecipeDTO recipeDTO, Integer userId) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Recipe not found"));

        if (recipe.getUser() == null ||!recipe.getUser().getId().equals(userId)){
            throw new UnauthorizedException("Only recipe creater can edit");
        }

        List<Tag> resolvedTags = new ArrayList<>();
        for (Tag t : recipeMapper.toEntity(recipeDTO).getTags()) {
            Tag resolvedTag = tagRepository.findByName(t.getName())
                    .orElseGet(() -> tagRepository.save(t));
            resolvedTags.add(resolvedTag);
        }

        recipe = ObjectMerger.mergeNonNullFields(recipe, recipeMapper.toEntity(recipeDTO));

        recipe.setTags(resolvedTags);

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDTO(recipe);
    }

    public void deleteRecipe(Integer id, Integer userId) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Recipe not found"));

        if (recipe.getUser() == null || !recipe.getUser().getId().equals(userId)){
            throw new UnauthorizedException("Only recipe creator can Delete it");
        }

        try {
            recipeRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Cannot delete recipe — it is used in  plans or cycles.");
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Recipe not found.");
        }
    }

}