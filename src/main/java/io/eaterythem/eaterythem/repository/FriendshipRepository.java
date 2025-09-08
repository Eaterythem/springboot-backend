package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Friendship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    // Additional query methods if needed
} 