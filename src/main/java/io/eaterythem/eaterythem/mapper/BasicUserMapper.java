package io.eaterythem.eaterythem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicUserMapper {

    BasicUserDTO toDTO(User user);

    User toEntity(BasicUserDTO basicUserDTO);

    List<User> toEntity(List<BasicUserDTO> sharedWith);
    
}
