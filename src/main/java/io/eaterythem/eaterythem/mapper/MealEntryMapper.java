package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.model.MealEntry;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RecipeMapper.class, BasicMealPlanMapper.class, MealVoteMapper.class})
public interface MealEntryMapper {

    MealEntryDTO toDTO(MealEntry mealEntry);
    
    MealEntry toEntity(MealEntryDTO mealEntryDTO);

    List<MealEntryDTO> toDTO(List<MealEntry> mealEntries);
} 