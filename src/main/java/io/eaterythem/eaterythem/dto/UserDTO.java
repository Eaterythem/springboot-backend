package io.eaterythem.eaterythem.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {
    private UUID id;

    private String email;
    
    private String name;

    private List<RecipeDTO> recipes;

    private List<MealPlanDTO> mealPlans;

    private List<MealCycleDTO> ownedCycles;

    private List<MealCycleDTO> sharedCycles;
}
