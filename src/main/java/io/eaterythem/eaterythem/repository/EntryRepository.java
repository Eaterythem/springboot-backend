package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Entry;



import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    // Additional query methods if needed
} 