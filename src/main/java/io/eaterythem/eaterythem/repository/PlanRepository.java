package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Plan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Query("SELECT m FROM Plan m JOIN m.participants p WHERE p.user.id = :userId")
    List<Plan> getByUserId(@Param("userId") Integer userId);
}