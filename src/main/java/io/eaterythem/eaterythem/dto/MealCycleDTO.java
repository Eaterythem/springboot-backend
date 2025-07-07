package io.eaterythem.eaterythem.dto;

import java.util.List;
import java.util.UUID;
import io.eaterythem.eaterythem.model.enums.MealType;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class MealCycleDTO {
    private UUID id;
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    @NotNull(message = "Meal type is required")
    private MealType mealType;
    @NotNull(message = "User ID is required")
    private UUID userId;
    private boolean isPublic;
    private List<UUID> sharedWithIds;
} 