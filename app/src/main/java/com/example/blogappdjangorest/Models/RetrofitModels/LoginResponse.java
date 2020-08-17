package com.example.blogappdjangorest.Models.RetrofitModels;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Token")
    String Token;

    @SerializedName("Id")
    String Id;

    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
