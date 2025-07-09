package io.eaterythem.eaterythem.dto;

import java.util.UUID;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private UUID id;
    private String email;
    private String name;

    private List<UUID> recipeIds;
    private List<UUID> mealPlanIds;
    private List<UUID> ownedCycleIds;
    private List<UUID> sharedCycleIds;
}
