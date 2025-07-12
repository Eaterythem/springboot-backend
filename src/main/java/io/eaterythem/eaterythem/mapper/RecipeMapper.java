package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.RecipeDTO;
import io.eaterythem.eaterythem.model.Recipe;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {TagMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {
    RecipeDTO toDTO(Recipe recipe);
    List<RecipeDTO> toDTO(List<Recipe> recipe);

    Recipe toEntity(RecipeDTO recipeDTO);
    List<Recipe> toEntity(List<RecipeDTO> recipeDTOs);
}
