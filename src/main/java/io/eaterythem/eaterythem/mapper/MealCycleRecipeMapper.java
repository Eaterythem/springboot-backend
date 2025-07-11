package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleRecipeDTO;
import io.eaterythem.eaterythem.model.MealCycleRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, RecipeMapper.class})
public interface MealCycleRecipeMapper {

    @Mapping(target = "cycleId", source = "cycle.id")
    MealCycleRecipeDTO toDTO(MealCycleRecipe mealCycleRecipe);

    @Mapping(source = "cycleId", target = "cycle.id")
    MealCycleRecipe toEntity(MealCycleRecipeDTO mealCycleRecipeDTO);
} 