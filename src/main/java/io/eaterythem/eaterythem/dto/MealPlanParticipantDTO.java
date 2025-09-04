package io.eaterythem.eaterythem.dto;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import lombok.Data;

@Data
public class MealPlanParticipantDTO {
    
    Integer id;

    ParticipantRole role;

    BasicUserDTO user;
}
