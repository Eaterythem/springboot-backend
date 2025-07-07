package io.eaterythem.eaterythem.dto;

import java.util.UUID;
import io.eaterythem.eaterythem.model.enums.MealType;
import io.eaterythem.eaterythem.model.enums.MealEntryStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class MealEntryDTO {
    private UUID id;
    @NotNull(message = "Meal plan ID is required")
    private UUID mealPlanId;
    @NotNull(message = "Day index is required")
    private Integer dayIndex;
    @NotNull(message = "Meal type is required")
    private MealType mealType;
    @NotNull(message = "Planned recipe ID is required")
    private UUID plannedRecipeId;
    private UUID actualRecipeId;
    @NotNull(message = "Status is required")
    private MealEntryStatus status;
    private String note;
} 