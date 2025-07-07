package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.model.MealCycle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MealCycleMapper {
    MealCycleMapper INSTANCE = Mappers.getMapper(MealCycleMapper.class);
    MealCycleDTO toDTO(MealCycle mealCycle);
    MealCycle toEntity(MealCycleDTO mealCycleDTO);
} 