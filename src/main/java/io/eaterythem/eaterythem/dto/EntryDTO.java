package io.eaterythem.eaterythem.dto;

import java.util.List;

import io.eaterythem.eaterythem.model.enums.EntryStatus;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
public class EntryDTO {
    private Integer id;
    
    private RecipeDTO plannedRecipe;
    
    private RecipeDTO actualRecipe;
    
    @NotNull(message = "Day index is required")
    private Integer dayIndex;
    
    @NotNull(message = "Status is required")
    private EntryStatus status;
    
    private String note;

    private List<VoteDTO> votes;
} 