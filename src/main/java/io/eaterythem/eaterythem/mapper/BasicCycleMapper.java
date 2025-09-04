package io.eaterythem.eaterythem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.model.MealCycle;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { BasicUserMapper.class, MealCycleMapper.class, TagMapper.class, RecipeMapper.class })
public interface BasicCycleMapper {

    MealCycle toEntity(MealCycleDTO mealCycleDTO);

    MealCycleDTO toDTO(MealCycle mealCycle);
}
