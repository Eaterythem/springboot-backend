package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.MealEntryStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "meal_entries")
public class MealEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder.Default
    private Integer dayIndex = 1;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MealEntryStatus status = MealEntryStatus.PENDING;

    private String note;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "planned_recipe_id")
    private Recipe plannedRecipe;

    @ManyToOne
    @JoinColumn(name = "actual_recipe_id")
    private Recipe actualRecipe;

    @OneToMany(mappedBy = "mealEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealVote> votes;
}
