package io.eaterythem.eaterythem.model;

import java.util.*;

import io.eaterythem.eaterythem.model.enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
    name = "meal_entries",
    uniqueConstraints = @UniqueConstraint(columnNames = {"meal_plan_id", "dayIndex", "mealType"})
)
public class MealEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private MealPlan mealPlan;

    private int dayIndex;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToOne
    @JoinColumn(name = "planned_recipe_id")
    private Recipe plannedRecipe;

    @ManyToOne
    @JoinColumn(name = "actual_recipe_id")
    private Recipe actualRecipe;

    @Enumerated(EnumType.STRING)
    private MealEntryStatus status = MealEntryStatus.PENDING;

    private String note;
}

