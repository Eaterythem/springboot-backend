package io.eaterythem.eaterythem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.eaterythem.eaterythem.dto.FriendshipDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.mapper.FriendshipMapper;
import io.eaterythem.eaterythem.model.Friendship;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.model.enums.FriendshipStatus;
import io.eaterythem.eaterythem.repository.FriendshipRepository;
import io.eaterythem.eaterythem.repository.UserRepository;
import io.eaterythem.eaterythem.tools.ObjectMerger;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FriendshipService {
    FriendshipMapper friendshipMapper;

    FriendshipRepository friendshipRepository;
    UserRepository userRepository;

    @Transactional
    public FriendshipDTO createOrAcceptFriendship(Integer friendId, Integer userId) {
        if (friendId.equals(userId)) {
            throw new BadRequestException("You cannot add yourself as a friend");
        }

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new BadRequestException("Friend not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        Optional<Friendship> existing = friendshipRepository
                .findBetweenUsers(user, friend);

        if (existing.isPresent()) {
            Friendship friendship = existing.get();
            if (friendship.getUser().getId().equals(user.getId())) {
                return friendshipMapper.toDTO(friendship);
            }
            if (friendship.getStatus() == FriendshipStatus.ACCEPTED) {
                return friendshipMapper.toDTO(friendship);
            }
            friendship.setStatus(FriendshipStatus.ACCEPTED);
            return friendshipMapper.toDTO(friendshipRepository.save(friendship));
        }

        Friendship newFriendship = Friendship.builder()
                .user(user)
                .friend(friend)
                .status(FriendshipStatus.PENDING)
                .build();

        return friendshipMapper.toDTO(friendshipRepository.save(newFriendship));
    }

    @Transactional
    public FriendshipDTO updateFriendship(Integer id, Integer userId, FriendshipDTO friendshipDTO) {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("friendship not found"));

        if (!friendship.getUser().getId().equals(userId) && !friendship.getFriend().getId().equals(userId)) {
            throw new BadRequestException("User not part of the friendship");
        }

        friendship = ObjectMerger.mergeNonNullFields(friendship, friendshipMapper.toEntity(friendshipDTO));

        return friendshipMapper.toDTO(friendshipRepository.save(friendship));
    }

    @Transactional
    public FriendshipDTO deleteFriendship(Integer id, Integer userId) {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("friendship not found"));

        if (!friendship.getUser().getId().equals(userId) && !friendship.getFriend().getId().equals(userId)) {
            throw new BadRequestException("User not part of the friendship");
        }

        friendshipRepository.delete(friendship);
        return friendshipMapper.toDTO(friendship);
    }

}
