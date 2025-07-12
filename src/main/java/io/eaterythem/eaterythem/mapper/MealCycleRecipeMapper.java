package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleRecipeDTO;
import io.eaterythem.eaterythem.model.MealCycleRecipe;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MealCycleMapper.class, RecipeMapper.class, BasicCycleMapper.class})
public interface MealCycleRecipeMapper {

    MealCycleRecipeDTO toDTO(MealCycleRecipe mealCycleRecipe);

    MealCycleRecipe toEntity(MealCycleRecipeDTO mealCycleRecipeDTO);

    List<MealCycleRecipe> toEntity(List<MealCycleRecipeDTO> mealCycleRecipeDTOs);
} 