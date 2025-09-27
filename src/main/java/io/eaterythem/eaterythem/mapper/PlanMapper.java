package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.PlanDTO;
import io.eaterythem.eaterythem.model.Plan;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CycleMapper.class, EntryMapper.class, ParticipantMapper.class})
public interface PlanMapper {

    @Mapping(source = "entries", target = "entries")
    PlanDTO toDTO(Plan Plan);

    @Mapping(target =  "entries", source = "entries")
    Plan toEntity(PlanDTO PlanDTO);

    List<PlanDTO> toDTO(List<Plan> Plans);
} 