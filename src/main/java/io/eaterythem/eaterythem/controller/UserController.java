package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.dto.UserProfileDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public UserProfileDTO me(@CurrentUser UserPrincipal user) {

        UserProfileDTO userProfileDTO = userService.me(user.getUserId());
        
        return userProfileDTO;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }


    @PostMapping("/upload-profile-pic")
    public UserDTO uploadProfilePic(@CurrentUser UserPrincipal user, @RequestParam("file") MultipartFile file) throws Exception {
        
        UserDTO userDTO = userService.updateUserProfilePic(user.getUserId(), file);
        
        return userDTO;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
} 