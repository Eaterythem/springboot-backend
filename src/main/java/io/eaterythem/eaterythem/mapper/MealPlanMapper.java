package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, MealEntryMapper.class})
public interface MealPlanMapper {
    MealPlanDTO toDTO(MealPlan mealPlan);
    MealPlan toEntity(MealPlanDTO mealPlanDTO);
} 