package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, MealEntryMapper.class})
public interface MealPlanMapper {

    @Mapping(target = "userId", source = "user.id")
    MealPlanDTO toDTO(MealPlan mealPlan);

    @Mapping(source = "userId", target = "user.id")
    MealPlan toEntity(MealPlanDTO mealPlanDTO);
} 