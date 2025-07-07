package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
    RecipeDTO toDTO(Recipe recipe);
    Recipe toEntity(RecipeDTO recipeDTO);
} 