package com.example.UserProfileAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name="users",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String name;
   private int age;
   private String email;
    private String password;
    private String roles;



    public User(){

   }
   public User(String name, int age, String email){
       this.name=name;
       this.age=age;
       this.email=email;

   }

    public User(String name, String email, int age, String encoded, String roleUser) {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getRoles(){
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
