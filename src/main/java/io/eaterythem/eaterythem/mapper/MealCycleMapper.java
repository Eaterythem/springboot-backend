package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.model.MealCycle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MealCycleRecipeMapper.class})
public interface MealCycleMapper {
    MealCycleDTO toDTO(MealCycle mealCycle);
    MealCycle toEntity(MealCycleDTO mealCycleDTO);
} 