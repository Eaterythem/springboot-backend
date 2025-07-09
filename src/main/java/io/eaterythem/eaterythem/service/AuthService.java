package io.eaterythem.eaterythem.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.eaterythem.eaterythem.dto.LoginDTO;
import io.eaterythem.eaterythem.dto.RegisterDTO;
import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.mapper.UserMapper;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public User register(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BadRequestException("Password don`t match please check again");
        }
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return userRepository.save(user);
    }

    public User login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isEmpty())
            throw new BadRequestException("User not found");
        return userOpt.get();
    }

    public UserDTO me(String email){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) throw new BadRequestException("User not found");
        
        return userMapper.toDTO(userOpt.get());
    }
}
