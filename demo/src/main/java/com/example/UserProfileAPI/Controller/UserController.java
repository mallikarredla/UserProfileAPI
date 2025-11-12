package com.example.UserProfileAPI.Controller;


import com.example.UserProfileAPI.Service.UserService;
import com.example.UserProfileAPI.dto.UserRequestDTO;
import com.example.UserProfileAPI.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   private final UserService service;

   public UserController(UserService service) {
      this.service = service;
   }

   // Create -> return 201 Created
   @PostMapping
   public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
      UserResponseDTO created = service.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(created);
   }

   // List all users (non-paged)
   @GetMapping
   public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
      return ResponseEntity.ok(service.getAllUsers());
   }

   // Get user by id
   @GetMapping("/{id}")
   public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
      return ResponseEntity.ok(service.getUserById(id));
   }

   // Get users by name (explicit path so it does NOT collide with GET /users)
   @GetMapping("/name/{name}")
   public ResponseEntity<List<UserResponseDTO>> getUserByName(@PathVariable String name) {
      return ResponseEntity.ok(service.getUserByName(name));
   }

   // Delete
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      service.deleteUser(id);
      return ResponseEntity.noContent().build();
   }

   // Paged listing (explicit path)
   @GetMapping("/paged")
   public ResponseEntity<Page<UserResponseDTO>> getUserPaged(
           @PageableDefault(size = 10, sort = "id") Pageable pageable) {
      return ResponseEntity.ok(service.getUsersPaged(pageable));
   }

   // Search by query param (explicit path)
   @GetMapping("/search")
   public ResponseEntity<Page<UserResponseDTO>> searchByName(
           @RequestParam String q,
           @PageableDefault(size = 10, sort = "id") Pageable pageable) {
      return ResponseEntity.ok(service.searchUsersByName(q, pageable));
   }
}
