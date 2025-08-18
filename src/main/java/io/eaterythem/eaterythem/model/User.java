package io.eaterythem.eaterythem.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<MealPlanParticipant> mealPlanParticipants;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "user")
    private List<MealVote> mealVotes;

    @OneToMany(mappedBy = "user")
    private List<MealCycle> mealCycles;

    @ManyToMany(mappedBy = "sharedWith")
    private List<MealCycle> sharedCycles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Friendship> friendRequests;
}
