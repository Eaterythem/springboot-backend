package io.eaterythem.eaterythem.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "user")
    private List<MealPlan> mealPlans;

    @OneToMany(mappedBy = "user")
    private List<MealCycle> ownedCycles;

    @ManyToMany(mappedBy = "sharedWith")
    private List<MealCycle> sharedCycles;

}

