package io.eaterythem.eaterythem.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_cycle_recipes")
public class MealCycleRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private MealCycle cycle;

    @ManyToOne
    private Recipe recipe;

    private Integer position;
}
