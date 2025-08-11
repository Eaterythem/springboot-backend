package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealEntry;



import org.springframework.data.jpa.repository.JpaRepository;

public interface MealEntryRepository extends JpaRepository<MealEntry, Integer> {
    // Additional query methods if needed
} 