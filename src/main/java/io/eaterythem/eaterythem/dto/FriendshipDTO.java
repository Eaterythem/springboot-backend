package io.eaterythem.eaterythem.dto;

import java.time.LocalDateTime;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.enums.FriendshipStatus;
import lombok.Data;

@Data
public class FriendshipDTO {
    private Integer id;
    
    private BasicUserDTO user;  // Who initiated the friendship
    
    private BasicUserDTO friend; // Who received the request
    
    private FriendshipStatus status;
    
    private LocalDateTime createdAt;
}
