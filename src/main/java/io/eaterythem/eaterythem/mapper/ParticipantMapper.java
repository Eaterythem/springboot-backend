package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanParticipantDTO;
import io.eaterythem.eaterythem.model.MealPlanParticipant;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {BasicUserMapper.class})
public interface ParticipantMapper {

    MealPlanParticipantDTO toDTO(MealPlanParticipant participant);
    
    MealPlanParticipant toEntity(MealPlanParticipantDTO participantDTO);

} 