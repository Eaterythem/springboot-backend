package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealVoteDTO;
import io.eaterythem.eaterythem.model.MealVote;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RecipeMapper.class, BasicMealPlanMapper.class})
public interface MealVoteMapper {

    MealVoteDTO toDTO(MealVote MealVote);
    
    MealVote toEntity(MealVoteDTO MealVoteDTO);

} 