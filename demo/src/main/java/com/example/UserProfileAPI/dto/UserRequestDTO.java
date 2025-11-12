package com.example.UserProfileAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import  jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = " Name is Required")
    private String name;

    @Email(message = "Please enter a valid email")
    private String email;

    @Min(value = 0, message = "Age must be a positive number")
    @Schema(example = "25")
    private Integer age;


}
//DTO has only fields, getter and setter methods to control the flexibility and limited exposure
//If the client wants to get only name and email , it limits to name and email only no exposure of age.
//Usage of DTO in real world scenario .