package io.eaterythem.eaterythem.dto.Basic;



import lombok.Data;

@Data
public class BasicUserDTO {
    private Integer id;

    private String profilePicUrl;

    private String email;
    
    private String name;
}
