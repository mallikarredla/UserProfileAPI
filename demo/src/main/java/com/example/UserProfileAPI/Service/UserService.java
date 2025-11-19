package com.example.UserProfileAPI.Service;

import com.example.UserProfileAPI.Repository.UserRepository;
import com.example.UserProfileAPI.dto.UserRequestDTO;
import com.example.UserProfileAPI.dto.UserResponseDTO;
import com.example.UserProfileAPI.exception.ResourceNotFoundException;
import com.example.UserProfileAPI.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = new User(dto.getName(), dto.getEmail(), dto.getAge());
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getAge());
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getAge()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    // <-- FIXED: accept UserRequestDTO here (input), still return UserResponseDTO
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        User updated = userRepository.save(user);
        return new UserResponseDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getAge());
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Cannot delete, User not found with id : " + id);
        }
        userRepository.deleteById(id);
    }
}
