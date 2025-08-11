package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.VoteType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_votes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"meal_entry_id", "user_id"}))
public class MealVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private String note;

    @ManyToOne
    @JoinColumn(name = "meal_entry_id")
    private MealEntry mealEntry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "replacement_recipe_id")
    private Recipe replacementRecipe;
}
