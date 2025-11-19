package com.example.UserProfileAPI.dto;

public class UserResponseDTO {


    private Long id;
    private String name;
    private String email;
    private int age;

    public UserResponseDTO(Long id, String name, String email, int age){
        this.id=id;
        this.email=email;
        this.age=age;
        this.name=name;
    }

  public Long getId(){
        return  id;
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
