package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    public List<UserDTO> getAllUsers() { return new ArrayList<>(); }
    public UserDTO getUserById(Integer id) { return null; }
    public UserDTO createUser(UserDTO userDTO) { return null; }
    public UserDTO updateUser(Integer id, UserDTO userDTO) { return null; }
    public void deleteUser(Integer id) {}
} 