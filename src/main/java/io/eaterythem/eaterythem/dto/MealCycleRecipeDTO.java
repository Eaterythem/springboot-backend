package io.eaterythem.eaterythem.dto;

import java.util.UUID;

import io.eaterythem.eaterythem.dto.Basic.BasicCycleDTO;
import lombok.Data;

@Data
public class MealCycleRecipeDTO {
    private UUID id;
    
    private BasicCycleDTO cycle;
    
    private RecipeDTO recipe;
    
    private Integer position;
} 