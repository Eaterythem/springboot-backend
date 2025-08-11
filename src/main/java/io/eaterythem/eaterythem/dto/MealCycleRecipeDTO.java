package io.eaterythem.eaterythem.dto;



import lombok.Data;

@Data
public class MealCycleRecipeDTO {
    private Integer id;
    
    private RecipeDTO recipe;
    
    private Integer position;
} 