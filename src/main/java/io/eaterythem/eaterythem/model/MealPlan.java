package io.eaterythem.eaterythem.model;

import java.util.*;

import io.eaterythem.eaterythem.model.enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meal_plans")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    private Date startDate;

    @Enumerated(EnumType.STRING)
    private MealPlanStatus status = MealPlanStatus.ACTIVE;

    @OneToOne
    @JoinColumn(name = "breakfast_cycle_id")
    private MealCycle breakfastCycle;

    @OneToOne
    @JoinColumn(name = "lunch_cycle_id")
    private MealCycle lunchCycle;

    @OneToOne
    @JoinColumn(name = "dinner_cycle_id")
    private MealCycle dinnerCycle;

    private int breakfastIndex = 0;

    private int lunchIndex = 0;
    
    private int DinnerIndex = 0;
    
    @OneToMany(mappedBy = "mealPlan")
    private List<MealEntry> entries;
}
