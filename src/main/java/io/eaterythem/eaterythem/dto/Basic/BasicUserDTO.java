package io.eaterythem.eaterythem.dto.Basic;

import java.util.UUID;

import lombok.Data;

@Data
public class BasicUserDTO {
    private UUID id;

    private String email;
    
    private String name;
}
