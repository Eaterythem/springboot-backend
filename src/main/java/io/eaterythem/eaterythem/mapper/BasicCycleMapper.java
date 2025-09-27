package io.eaterythem.eaterythem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.model.Cycle;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { BasicUserMapper.class, CycleMapper.class, TagMapper.class, RecipeMapper.class })
public interface BasicCycleMapper {

    Cycle toEntity(CycleDTO cycleDTO);

    CycleDTO toDTO(Cycle cycle);
}
