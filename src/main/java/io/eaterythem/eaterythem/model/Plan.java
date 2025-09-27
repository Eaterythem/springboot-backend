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
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @ManyToOne
    @JoinColumn(name = "breakfast_cycle_id")
    private Cycle breakfastCycle;
    @ManyToOne
    @JoinColumn(name = "lunch_cycle_id")
    private Cycle lunchCycle;
    @ManyToOne
    @JoinColumn(name = "dinner_cycle_id")
    private Cycle dinnerCycle;

    private Integer breakfastIndex;
    private Integer lunchIndex;
    private Integer dinnerIndex;

    @OneToMany(mappedBy = "Plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanParticipant> participants;

    @OneToMany(mappedBy = "Plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entry> entries;
}
