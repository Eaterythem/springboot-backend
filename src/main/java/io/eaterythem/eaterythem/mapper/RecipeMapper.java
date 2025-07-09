package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.model.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TagMapper.class, MealCycleRecipeMapper.class})
public interface RecipeMapper {
    RecipeDTO toDTO(Recipe recipe);
    Recipe toEntity(RecipeDTO recipeDTO);
} 