package io.eaterythem.eaterythem.dto.Basic;

import java.util.Date;


import io.eaterythem.eaterythem.model.enums.PlanStatus;
import lombok.Data;

@Data
public class BasicPlanDTO {
    private Integer id;

    private BasicUserDTO user;
    
    private Date startDate;
    
    private PlanStatus status;
}
