package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.MealType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String instructions;

    private String ingredients;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
        name = "recipe_tags",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "recipe")
    private List<CycleRecipe> CycleRecipes;
}
