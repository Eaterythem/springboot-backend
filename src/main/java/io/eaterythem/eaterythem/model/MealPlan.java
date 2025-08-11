package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.PlanStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_plans")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    private Integer breakfastCycle;
    private Integer lunchCycle;
    private Integer dinnerCycle;

    private Integer breakfastIndex;
    private Integer lunchIndex;
    private Integer dinnerIndex;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "mealPlan")
    private List<MealPlanParticipant> participants;

    @OneToMany(mappedBy = "mealPlan")
    private List<MealEntry> mealEntries;
}
