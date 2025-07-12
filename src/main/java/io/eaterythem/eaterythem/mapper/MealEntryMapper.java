package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.model.MealEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, BasicMealPlanMapper.class})
public interface MealEntryMapper {

    MealEntryDTO toDTO(MealEntry mealEntry);
    
    MealEntry toEntity(MealEntryDTO mealEntryDTO);
} 