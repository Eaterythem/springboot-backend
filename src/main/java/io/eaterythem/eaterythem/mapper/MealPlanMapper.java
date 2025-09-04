package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealPlan;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {MealCycleMapper.class, MealEntryMapper.class, ParticipantMapper.class})
public interface MealPlanMapper {

    @Mapping(source = "mealEntries", target = "entries")
    MealPlanDTO toDTO(MealPlan mealPlan);

    @Mapping(target =  "mealEntries", source = "entries")
    MealPlan toEntity(MealPlanDTO mealPlanDTO);

    List<MealPlanDTO> toDTO(List<MealPlan> mealPlans);
} 