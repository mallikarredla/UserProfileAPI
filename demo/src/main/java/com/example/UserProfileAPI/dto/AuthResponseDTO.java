package com.example.UserProfileAPI.dto;

public class AuthResponseDTO {

    private String token;
    private String tokenType="Bearer";



    public AuthResponseDTO(String token){
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
