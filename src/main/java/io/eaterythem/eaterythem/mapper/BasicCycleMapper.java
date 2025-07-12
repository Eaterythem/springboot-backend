package io.eaterythem.eaterythem.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.model.MealCycle;

@Mapper(componentModel = "spring")
public interface BasicCycleMapper {
    

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "mealType", ignore = true)
    @Mapping(target = "recipes", ignore = true)
    @Mapping(target = "sharedWith", ignore = true)
    MealCycle toEntity(MealCycleDTO mealCycleDTO);

    MealCycleDTO toDTO(MealCycle mealCycle);
}
