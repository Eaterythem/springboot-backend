package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.Basic.BasicMealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {BasicUserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicMealPlanMapper {
    
    BasicMealPlanDTO toDTO(MealPlan mealPlan);

    MealPlan toEntity(BasicMealPlanDTO basicMealPlanDTO);
}
