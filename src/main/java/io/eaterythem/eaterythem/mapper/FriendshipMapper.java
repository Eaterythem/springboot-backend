package io.eaterythem.eaterythem.mapper;

import org.mapstruct.Mapper;

import io.eaterythem.eaterythem.dto.FriendshipDTO;
import io.eaterythem.eaterythem.model.Friendship;

@Mapper(componentModel = "spring", uses = {BasicUserMapper.class})
public interface FriendshipMapper {

    Friendship toEntity(FriendshipDTO friendshipDTO);

    FriendshipDTO toDTO(Friendship friendship);
    
}
