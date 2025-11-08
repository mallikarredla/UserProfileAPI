package com.example.UserProfileAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import  jakarta.validation.constraints.Min;
public class UserRequestDTO {

    @NotBlank(message = " Name is Required")
    private String name;

    @Email(message = "Please enter a valid email")
    private String email;

    @Min(value = 0, message = "Age must be a positive number")
    private int age;


    public String getName(){
        return name;
    }
public void setName(String name){
        this.name=name;
}
   public String getEmail(){
        return email;
   }
   public void setEmail(String email){
        this.email=email;
   }
public int getAge(){
        return age;

}
public void setAge(int age){
        this.age=age;
}
}

//DTO has only fields, getter and setter methods to control the flexibility and limited exposure
//If the client wants to get only name and email , it limits to name and email only no exposure of age.
//Usage of DTO in real world scenario .