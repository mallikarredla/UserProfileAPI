package com.example.UserProfileAPI.dto;

public class UserResponseDTO {

    private Long id;
    private int age;
    private String name;
    private String email;

    public UserResponseDTO(Long id, String name, String email, int age){
        this.id=id;
        this.name=name;
        this.email=email;
        this.age=age;
    }
public Long getId(){
        return id;

}
public String getName(){
        return name;
}
public String getEmail(){
        return email;

}
public int getAge(){
        return age;

}
}
