package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.EntryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder.Default
    private Integer dayIndex = 1;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EntryStatus status = EntryStatus.PENDING;

    private String note;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "planned_recipe_id")
    private Recipe plannedRecipe;

    @ManyToOne
    @JoinColumn(name = "actual_recipe_id")
    private Recipe actualRecipe;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;
}
