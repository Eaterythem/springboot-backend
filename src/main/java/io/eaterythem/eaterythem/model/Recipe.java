package io.eaterythem.eaterythem.model;

import java.util.*;

import io.eaterythem.eaterythem.model.enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    private String name;

    private String instructions;
    
    private String ingredients;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToMany
    @JoinTable(
        name = "recipe_tags",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "recipe")
    private List<MealCycleRecipe> cycles;
}