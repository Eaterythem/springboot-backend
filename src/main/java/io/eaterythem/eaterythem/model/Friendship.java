package io.eaterythem.eaterythem.model;

import java.time.LocalDateTime;

import io.eaterythem.eaterythem.model.enums.FriendshipStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "friendships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Who initiated the friendship
    
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend; // Who received the request
    
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;
    
    private LocalDateTime createdAt;
}
