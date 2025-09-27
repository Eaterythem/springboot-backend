package io.eaterythem.eaterythem.dto;

import io.eaterythem.eaterythem.dto.Basic.BasicUserDTO;
import io.eaterythem.eaterythem.model.enums.VoteType;
import lombok.Data;

@Data
public class VoteDTO {
    int id;
    VoteType voteType;
    Integer entryId;
    String note;
    BasicUserDTO user;
    RecipeDTO replacementRecipe;
}
