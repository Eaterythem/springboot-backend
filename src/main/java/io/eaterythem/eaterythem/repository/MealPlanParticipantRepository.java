package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealPlanParticipantRepository extends JpaRepository<MealPlan, Integer> {

    
    @Query("SELECT p.role " +
        "FROM MealPlanParticipant p " +
        "WHERE p.user.id = :userId " +
        "AND p.mealPlan.id = :mealPlanId")
    ParticipantRole getUserRole(@Param("userId") int userId,
                                @Param("mealPlanId") int mealPlanId);
} 