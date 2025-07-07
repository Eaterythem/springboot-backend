package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MealPlanMapper {
    MealPlanMapper INSTANCE = Mappers.getMapper(MealPlanMapper.class);
    MealPlanDTO toDTO(MealPlan mealPlan);
    MealPlan toEntity(MealPlanDTO mealPlanDTO);
} 