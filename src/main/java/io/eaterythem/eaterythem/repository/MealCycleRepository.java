package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealCycle;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCycleRepository extends JpaRepository<MealCycle, UUID> {

    List<MealCycle> findByUserId(UUID userId);
} 