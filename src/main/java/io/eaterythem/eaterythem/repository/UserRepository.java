package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Additional query methods if needed
    Optional<User> findByEmail(String email);
} 