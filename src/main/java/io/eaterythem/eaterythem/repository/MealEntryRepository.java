package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.MealEntry;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealEntryRepository extends JpaRepository<MealEntry, UUID> {
    // Additional query methods if needed
} 