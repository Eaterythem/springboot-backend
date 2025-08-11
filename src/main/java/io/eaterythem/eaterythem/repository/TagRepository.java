package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Tag;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Optional<Tag> findByName(String name);

} 