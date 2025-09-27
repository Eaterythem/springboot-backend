package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.model.Cycle;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CycleRecipeMapper.class, BasicUserMapper.class})
public interface CycleMapper {

    CycleDTO toDTO(Cycle Cycle);
    
    List<CycleDTO> toDTO(List<Cycle> Cycles);

    // @Mapping(target = "sharedWith", ignore = true)
    Cycle toEntity(CycleDTO CycleDTO);
} 