package io.eaterythem.eaterythem.dto;

import java.util.List;


import lombok.Data;

@Data
public class UserDTO {
    private Integer id;

    private String profilePicUrl;

    private String email;
    
    private String name;

    private List<RecipeDTO> recipes;

    private List<PlanDTO> plans;

    private List<CycleDTO> cycles;

    private List<CycleDTO> sharedCycles;
}
