package com.example.blogappdjangorest.Models.RetrofitModels;

public class LoginResponse {

    String Token;
    String Id;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
