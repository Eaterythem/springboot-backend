package io.eaterythem.eaterythem.dto;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.enums.VoteType;
import lombok.Data;

@Data
public class MealVoteDTO {
    int id;
    VoteType voteType;
    Integer mealEntryId;
    String note;
    BasicUserDTO user;
    RecipeDTO replacementRecipe;
}
