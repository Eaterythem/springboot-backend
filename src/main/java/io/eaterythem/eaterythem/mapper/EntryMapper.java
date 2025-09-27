package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.EntryDTO;
import io.eaterythem.eaterythem.model.Entry;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RecipeMapper.class, BasicPlanMapper.class, VoteMapper.class})
public interface EntryMapper {

    EntryDTO toDTO(Entry entry);
    
    Entry toEntity(EntryDTO entryDTO);

    List<EntryDTO> toDTO(List<Entry> entries);
} 