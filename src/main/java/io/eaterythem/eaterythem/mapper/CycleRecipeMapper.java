package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.CycleRecipeDTO;
import io.eaterythem.eaterythem.model.CycleRecipe;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CycleMapper.class, RecipeMapper.class, BasicCycleMapper.class})
public interface CycleRecipeMapper {

    CycleRecipeDTO toDTO(CycleRecipe CycleRecipe);

    CycleRecipe toEntity(CycleRecipeDTO CycleRecipeDTO);

    List<CycleRecipe> toEntity(List<CycleRecipeDTO> CycleRecipeDTOs);
} 