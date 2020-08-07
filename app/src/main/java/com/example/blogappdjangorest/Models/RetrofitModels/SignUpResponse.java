package com.example.blogappdjangorest.Models.RetrofitModels;

public class SignUpResponse {

    String Response;

    String Token;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
