package io.eaterythem.eaterythem.repository;

import io.eaterythem.eaterythem.model.Friendship;
import io.eaterythem.eaterythem.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

    @Query("""
                SELECT f FROM Friendship f
                WHERE (f.user = :user AND f.friend = :friend)
            """)
    Optional<Friendship> findByUserAndFriend(@Param("user") User user, @Param("friend") User friend);

    @Query("""
                SELECT f FROM Friendship f
                WHERE (f.user = :user AND f.friend = :friend)
                OR (f.user = :friend AND f.friend = :user)
            """)
    Optional<Friendship> findBetweenUsers(@Param("user") User user, @Param("friend") User friend);

}