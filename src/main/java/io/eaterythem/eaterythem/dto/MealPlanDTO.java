package io.eaterythem.eaterythem.dto;

import java.util.Date;
import java.util.UUID;
import io.eaterythem.eaterythem.model.enums.MealPlanStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class MealPlanDTO {
    private UUID id;
    @NotNull(message = "User ID is required")
    private UUID userId;
    @NotNull(message = "Start date is required")
    private Date startDate;
    @NotNull(message = "Status is required")
    private MealPlanStatus status;
    private UUID breakfastCycleId;
    private UUID lunchCycleId;
    private UUID dinnerCycleId;
    private int breakfastIndex;
    private int lunchIndex;
    private int dinnerIndex;
} 