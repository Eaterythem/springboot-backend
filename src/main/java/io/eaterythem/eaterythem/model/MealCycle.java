package io.eaterythem.eaterythem.model;

import java.util.*;

import io.eaterythem.eaterythem.model.enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_cycles")
public class MealCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToOne
    private User user;

    private boolean isPublic = false;

    @OneToMany(mappedBy = "cycle")
    private List<MealCycleRecipe> recipes;

    @ManyToMany
    @JoinTable(
        name = "cycle_shared_with",
        joinColumns = @JoinColumn(name = "cycle_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> sharedWith;
}
