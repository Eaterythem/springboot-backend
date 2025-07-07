package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.model.MealEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MealEntryMapper {
    MealEntryMapper INSTANCE = Mappers.getMapper(MealEntryMapper.class);
    MealEntryDTO toDTO(MealEntry mealEntry);
    MealEntry toEntity(MealEntryDTO mealEntryDTO);
} 