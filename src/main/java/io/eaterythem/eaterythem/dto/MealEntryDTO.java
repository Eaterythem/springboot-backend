package io.eaterythem.eaterythem.dto;

import io.eaterythem.eaterythem.model.enums.MealType;

import java.util.UUID;

import io.eaterythem.eaterythem.model.enums.MealEntryStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class MealEntryDTO {
    private UUID id;
    
    private RecipeDTO plannedRecipe;
    
    private RecipeDTO actualRecipe;
    
    @NotNull(message = "Day index is required")
    private Integer dayIndex;
    
    @NotNull(message = "Meal type is required")
    private MealType mealType;
    
    @NotNull(message = "Status is required")
    private MealEntryStatus status;
    
    private String note;
} 