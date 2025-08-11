package io.eaterythem.eaterythem.dto;

import java.util.List;

import io.eaterythem.eaterythem.model.enums.MealType;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class RecipeDTO {
    private Integer id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Instructions are required")
    private String instructions;
    
    @NotBlank(message = "Ingredients are required")
    private String ingredients;
    
    @NotNull(message = "Meal type is required")
    private MealType mealType;
    
    private List<TagDTO> tags;
    
} 