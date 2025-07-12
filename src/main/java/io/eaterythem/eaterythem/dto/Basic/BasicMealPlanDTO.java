package io.eaterythem.eaterythem.dto.Basic;

import java.util.Date;
import java.util.UUID;

import io.eaterythem.eaterythem.model.enums.MealPlanStatus;
import lombok.Data;

@Data
public class BasicMealPlanDTO {
    private UUID id;

    private BasicUserDTO user;
    
    private Date startDate;
    
    private MealPlanStatus status;
}
