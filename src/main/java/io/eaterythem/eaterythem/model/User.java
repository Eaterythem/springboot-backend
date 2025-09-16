package io.eaterythem.eaterythem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import io.eaterythem.eaterythem.model.enums.FriendshipStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String profilePicUrl;

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
    private List<Friendship> sentFriendRequests;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    private List<Friendship> receivedFriendRequests;

    @Transient
    public List<Friendship> getFriendRequests() {
        List<Friendship> all = new ArrayList<>();
        if (sentFriendRequests != null)
            all.addAll(sentFriendRequests);
        if (receivedFriendRequests != null)
            all.addAll(receivedFriendRequests);
        return all;
    }

    @Transient
    public List<User> getFriends() {
        return getFriendRequests().stream()
                .filter(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
                .map(f -> f.getUser().equals(this) ? f.getFriend() : f.getUser())
                .toList();
    }

}
