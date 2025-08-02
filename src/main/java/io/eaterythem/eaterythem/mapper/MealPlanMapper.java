package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, MealEntryMapper.class, BasicUserMapper.class})
public interface MealPlanMapper {

    MealPlanDTO toDTO(MealPlan mealPlan);

    MealPlan toEntity(MealPlanDTO mealPlanDTO);

    List<MealPlanDTO> toDTO(List<MealPlan> mealPlans);
} 