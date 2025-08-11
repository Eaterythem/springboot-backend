package io.eaterythem.eaterythem.dto;

import java.util.Date;


import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.enums.MealPlanStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class MealPlanDTO {
    private Integer id;
    
    private BasicUserDTO user;
    
    @NotNull(message = "Start date is required")
    private Date startDate;
    
    @NotNull(message = "Status is required")
    private MealPlanStatus status;

    private MealCycleDTO breakfastCycle;
    
    private MealCycleDTO lunchCycle;
    
    private MealCycleDTO dinnerCycle;
    
    private List<MealEntryDTO> entries;
    
    private int breakfastIndex;
    
    private int lunchIndex;
    
    private int dinnerIndex;
}