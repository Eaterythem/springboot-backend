package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealCycle;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCycleRepository extends JpaRepository<MealCycle, UUID> {
    // Additional query methods if needed
} 