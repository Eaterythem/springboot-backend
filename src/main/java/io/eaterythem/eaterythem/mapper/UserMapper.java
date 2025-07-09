package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
} 