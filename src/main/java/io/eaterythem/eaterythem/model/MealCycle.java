package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.MealType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_cycles")
public class MealCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
