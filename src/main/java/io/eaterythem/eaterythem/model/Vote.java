package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.VoteType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "votes", uniqueConstraints = @UniqueConstraint(columnNames = { "entry_id", "user_id" }))
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private String note;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "replacement_recipe_id")
    private Recipe replacementRecipe;
}
