package com.example.UserProfileAPI.Controller;

import com.example.UserProfileAPI.Service.UserService;
import com.example.UserProfileAPI.dto.UserRequestDTO;
import com.example.UserProfileAPI.dto.UserResponseDTO;
import com.example.UserProfileAPI.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

@GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO>getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO>updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO user){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
