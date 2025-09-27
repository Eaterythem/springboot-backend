package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Cycle;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepository extends JpaRepository<Cycle, Integer> {

    List<Cycle> findByUserId(Integer userId);
} 