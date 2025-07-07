package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    // Additional query methods if needed
} 