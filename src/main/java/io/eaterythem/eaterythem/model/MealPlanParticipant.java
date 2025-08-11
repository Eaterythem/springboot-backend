package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_plan_participants",
       uniqueConstraints = @UniqueConstraint(columnNames = {"meal_plan_id", "user_id"}))
public class MealPlanParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ParticipantRole role;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
