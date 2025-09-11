package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealVoteDTO;
import io.eaterythem.eaterythem.model.MealVote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RecipeMapper.class, BasicMealPlanMapper.class, BasicUserMapper.class})
public interface MealVoteMapper {

    @Mapping(target = "mealEntryId", source = "mealEntry.id")
    MealVoteDTO toDTO(MealVote mealVote);
    
    @Mapping(target = "mealEntry.id", source = "mealEntryId")
    MealVote toEntity(MealVoteDTO mealVoteDTO);

} 