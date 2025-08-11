package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.dto.auth.LoginDTO;
import io.eaterythem.eaterythem.dto.auth.RegisterDTO;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.security.jwt.JwtUtil;
import io.eaterythem.eaterythem.service.AuthService;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody @Valid RegisterDTO registerDTO) {

        User user = authService.register(registerDTO);

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Valid LoginDTO loginDTO) {
        
        User user = authService.login(loginDTO);
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @GetMapping("/me")
    public UserDTO me(@CurrentUser UserPrincipal user) {

        UserDTO userDTO = authService.me(user.getUsername());
        
        return userDTO;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
} 