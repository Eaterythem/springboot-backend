package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.model.Cycle;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CycleRecipeMapper.class, BasicUserMapper.class})
public interface CycleMapper {

    CycleDTO toDTO(Cycle cycle);
    
    List<CycleDTO> toDTO(List<Cycle> cycles);

    // @Mapping(target = "sharedWith", ignore = true)
    Cycle toEntity(CycleDTO cycleDTO);
} 