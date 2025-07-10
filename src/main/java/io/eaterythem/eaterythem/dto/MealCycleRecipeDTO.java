package io.eaterythem.eaterythem.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class MealCycleRecipeDTO {
    private UUID id;
    
    private UUID cycleId;
    
    private RecipeDTO recipe;
    
    private Integer position;
} 