package io.eaterythem.eaterythem.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class MealCycleRecipeDTO {
    private UUID id;
    
    private MealCycleDTO cycle;
    
    private RecipeDTO recipe;
    
    private Integer position;
} 