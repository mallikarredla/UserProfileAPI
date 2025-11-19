package com.example.UserProfileAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;
    @Min(value = 0,message = "age should me above 0")
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
