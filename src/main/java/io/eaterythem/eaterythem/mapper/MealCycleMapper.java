package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.model.MealCycle;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MealCycleRecipeMapper.class, BasicUserMapper.class})
public interface MealCycleMapper {

    MealCycleDTO toDTO(MealCycle mealCycle);
    
    List<MealCycleDTO> toDTO(List<MealCycle> mealCycles);

    // @Mapping(target = "sharedWith", ignore = true)
    MealCycle toEntity(MealCycleDTO mealCycleDTO);
} 