package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.model.MealEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface MealEntryMapper {

    @Mapping(source = "mealPlan.id", target = "mealPlanId")
    MealEntryDTO toDTO(MealEntry mealEntry);
    
    @Mapping(source = "mealPlanId", target = "mealPlan.id")
    MealEntry toEntity(MealEntryDTO mealEntryDTO);
} 