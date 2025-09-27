package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.PlanParticipantDTO;
import io.eaterythem.eaterythem.model.PlanParticipant;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {BasicUserMapper.class})
public interface ParticipantMapper {

    PlanParticipantDTO toDTO(PlanParticipant participant);
    
    PlanParticipant toEntity(PlanParticipantDTO participantDTO);

} 