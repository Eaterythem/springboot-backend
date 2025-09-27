package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.Basic.BasicPlanDTO;
import io.eaterythem.eaterythem.model.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {BasicUserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicPlanMapper {
    
    BasicPlanDTO toDTO(Plan plan);

    Plan toEntity(BasicPlanDTO basicPlanDTO);
}
