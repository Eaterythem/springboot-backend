package io.eaterythem.eaterythem.dto;

import io.eaterythem.eaterythem.model.enums.VoteType;

public class MealVoteDTO {
    int id;
    VoteType voteType;
    String note;
    RecipeDTO recipe;
}
