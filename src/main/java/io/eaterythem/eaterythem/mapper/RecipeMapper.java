package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.model.Recipe;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TagMapper.class, MealCycleRecipeMapper.class})
public interface RecipeMapper {
    RecipeDTO toDTO(Recipe recipe);
    List<RecipeDTO> toDTO(List<Recipe> recipe);

    @Mapping(target = "cycles", ignore = true)
    @Mapping(target = "user", ignore = true)
    Recipe toEntity(RecipeDTO recipeDTO);
} 