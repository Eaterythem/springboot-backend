package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    public List<UserDTO> getAllUsers() { return new ArrayList<>(); }
    public UserDTO getUserById(UUID id) { return null; }
    public UserDTO createUser(UserDTO userDTO) { return null; }
    public UserDTO updateUser(UUID id, UserDTO userDTO) { return null; }
    public void deleteUser(UUID id) {}
} 