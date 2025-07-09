package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleRecipeDTO;
import io.eaterythem.eaterythem.model.MealCycleRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, RecipeMapper.class})
public interface MealCycleRecipeMapper {
    MealCycleRecipeDTO toDTO(MealCycleRecipe mealCycleRecipe);
    MealCycleRecipe toEntity(MealCycleRecipeDTO mealCycleRecipeDTO);
} 