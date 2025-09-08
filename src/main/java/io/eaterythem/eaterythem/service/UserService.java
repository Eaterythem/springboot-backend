package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.dto.UserProfileDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.mapper.UserProfileMapper;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserProfileMapper userProfileMapper;

    public List<UserDTO> getAllUsers() { return new ArrayList<>(); }
    public UserDTO getUserById(Integer id) { return null; }
    public UserDTO createUser(UserDTO userDTO) { return null; }
    public UserDTO updateUser(Integer id, UserDTO userDTO) { return null; }
    public void deleteUser(Integer id) {}

    public UserProfileDTO me(Integer userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new BadRequestException("User not found"));

        return userProfileMapper.toUserProfileDTO(user);

    }
} 