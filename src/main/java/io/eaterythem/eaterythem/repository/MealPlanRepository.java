package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealPlan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {

    @Query("SELECT m FROM MealPlan m JOIN m.participants p WHERE p.user.id = :userId")
    List<MealPlan> getByUserId(@Param("userId") Integer userId);
}