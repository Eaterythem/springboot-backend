package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.UserProfileDTO;
import io.eaterythem.eaterythem.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BasicUserMapper.class})
public interface UserProfileMapper {

    UserProfileDTO toUserProfileDTO(User user);

}
