package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealCycleRepository extends JpaRepository<MealCycle, Long> {
    // Additional query methods if needed
} 