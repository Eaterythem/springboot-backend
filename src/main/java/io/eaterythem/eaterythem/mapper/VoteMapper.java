package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.VoteDTO;
import io.eaterythem.eaterythem.model.Vote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RecipeMapper.class, BasicPlanMapper.class, BasicUserMapper.class})
public interface VoteMapper {

    @Mapping(target = "entryId", source = "entry.id")
    VoteDTO toDTO(Vote Vote);
    
    @Mapping(target = "entry.id", source = "entryId")
    Vote toEntity(VoteDTO VoteDTO);

} 