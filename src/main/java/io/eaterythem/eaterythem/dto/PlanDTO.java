package io.eaterythem.eaterythem.dto;

import java.util.Date;

import io.eaterythem.eaterythem.model.enums.PlanStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class PlanDTO {
    private Integer id;

    String name;
        
    @NotNull(message = "Start date is required")
    private Date startDate;
    
    @NotNull(message = "Status is required")
    private PlanStatus status;

    private CycleDTO breakfastCycle;
    
    private CycleDTO lunchCycle;
    
    private CycleDTO dinnerCycle;
    
    private List<EntryDTO> entries;
    
    private EntryDTO breakfastEntry;
    
    private EntryDTO lunchEntry;
    
    private EntryDTO dinnerEntry;

    private List<PlanParticipantDTO> participants;
}