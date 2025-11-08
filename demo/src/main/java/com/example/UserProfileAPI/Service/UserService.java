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

    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository=repository;

    }
   public UserResponseDTO createUser(UserRequestDTO DTO){
        User user=new User(DTO.getName(),DTO.getAge(),DTO.getEmail());
        User saved=repository.save(user);
        return new UserResponseDTO(saved.getId(),saved.getName(),saved.getEmail(),saved.getAge());
   }
   public List<UserResponseDTO>getAllUsers(){
        return repository.findAll().stream().map(u->new UserResponseDTO(u.getId(),u.getName(),u.getEmail(),u.getAge())).collect(Collectors.toList());
   }
   public UserResponseDTO getUserById(Long id){
        User user=repository.findById(id).orElseThrow(()->new RuntimeException("User not found with ID: " +id));
        return new UserResponseDTO(user.getId(),user.getName(),user.getEmail(),user.getAge());
   }
   public List<UserResponseDTO> getUserByName(String name){
        List<User>users=repository.findByNameIgnoreCase(name);

        if(users.isEmpty()){
            throw new ResourceNotFoundException("NO User found with that name: " +name);
        }
        return users.stream().map(u-> new UserResponseDTO(u.getId(),u.getName(),u.getEmail(),u.getAge())).collect(Collectors.toList());
   }
   public UserResponseDTO updateUser(Long id,UserRequestDTO DTO){

        User user=repository.findById(id).orElseThrow(()->new RuntimeException("User not found with id:" +id));
        user.setAge(DTO.getAge());
        user.setEmail(DTO.getEmail());
        user.setName(DTO.getName());
        User updated=repository.save(user);
        return new UserResponseDTO(updated.getId(),updated.getName(),updated.getEmail(),updated.getAge());
   }
   public void deleteUser(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Cannot delete, User not found with id: " +id);


        }
        repository.deleteById(id);

   }
}
