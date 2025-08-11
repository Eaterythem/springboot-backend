package io.eaterythem.eaterythem.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_cycle_recipes")
public class MealCycleRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private MealCycle cycle;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
