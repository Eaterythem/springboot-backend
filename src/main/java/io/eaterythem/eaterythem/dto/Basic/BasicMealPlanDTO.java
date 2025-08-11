package io.eaterythem.eaterythem.dto.Basic;

import java.util.Date;


import io.eaterythem.eaterythem.model.enums.MealPlanStatus;
import lombok.Data;

@Data
public class BasicMealPlanDTO {
    private Integer id;

    private BasicUserDTO user;
    
    private Date startDate;
    
    private MealPlanStatus status;
}
