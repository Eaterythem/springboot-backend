package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealCycle;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCycleRepository extends JpaRepository<MealCycle, Integer> {

    List<MealCycle> findByUserId(Integer userId);
} 