package io.eaterythem.eaterythem.dto;



import lombok.Data;

@Data
public class CycleRecipeDTO {
    private Integer id;
    
    private RecipeDTO recipe;
    
    private Integer position;
} 