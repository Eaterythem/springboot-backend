package io.eaterythem.eaterythem.dto;

import java.util.List;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Integer id;

    private String profilePicUrl;

    private String name;
    
    private String email;

    private List<BasicUserDTO> friends;

    private List<FriendshipDTO> friendRequests;
}
