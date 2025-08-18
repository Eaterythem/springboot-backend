package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.MealPlanStatus;
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

    private String name;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private MealPlanStatus status;

    @ManyToOne
    @JoinColumn(name = "breakfast_cycle_id")
    private MealCycle breakfastCycle;
    @ManyToOne
    @JoinColumn(name = "lunch_cycle_id")
    private MealCycle lunchCycle;
    @ManyToOne
    @JoinColumn(name = "dinner_cycle_id")
    private MealCycle dinnerCycle;

    private Integer breakfastIndex;
    private Integer lunchIndex;
    private Integer dinnerIndex;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPlanParticipant> participants;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealEntry> mealEntries;
}
