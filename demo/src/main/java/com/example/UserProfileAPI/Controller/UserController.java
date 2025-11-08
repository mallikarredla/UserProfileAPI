package com.example.UserProfileAPI.Controller;

import com.example.UserProfileAPI.Service.UserService;
import com.example.UserProfileAPI.dto.UserRequestDTO;
import com.example.UserProfileAPI.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService service;

   public UserController(UserService service){
      this.service=service;
   }
   @PostMapping
   public ResponseEntity<UserResponseDTO>createUser(@Valid @RequestBody UserRequestDTO user){
      return ResponseEntity.ok(service.createUser(user));
   }
   @GetMapping
   public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
      return ResponseEntity.ok(service.getAllUsers());
   }

@GetMapping("/{id}")
   public ResponseEntity<UserResponseDTO>getUserById(@PathVariable Long id){
      return ResponseEntity.ok(service.getUserById( id));
   }
   public ResponseEntity<UserResponseDTO>getUserByName(@PathVariable String name){
      return ResponseEntity.ok((UserResponseDTO) service.getUserByName(name));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void>deleteUser(@PathVariable Long id){
      service.deleteUser(id);
      return ResponseEntity.noContent().build();
   }

}
