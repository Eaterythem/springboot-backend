package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Plan;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanParticipantRepository extends JpaRepository<Plan, Integer> {

    
    @Query("SELECT p.role " +
        "FROM PlanParticipant p " +
        "WHERE p.user.id = :userId " +
        "AND p.plan.id = :planId")
    ParticipantRole getUserRole(@Param("userId") int userId,
                                @Param("planId") int planId);
} 