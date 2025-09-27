package io.eaterythem.eaterythem.model;

import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "plan_participants",
       uniqueConstraints = @UniqueConstraint(columnNames = {"plan_id", "user_id"}))
public class PlanParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ParticipantRole role;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
