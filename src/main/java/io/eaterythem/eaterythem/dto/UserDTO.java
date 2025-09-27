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

    private List<PlanDTO> Plans;

    private List<CycleDTO> Cycles;

    private List<CycleDTO> sharedCycles;
}
