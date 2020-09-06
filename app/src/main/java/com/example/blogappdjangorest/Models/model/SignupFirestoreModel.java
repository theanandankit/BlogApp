package com.example.blogappdjangorest.Models.model;

public class SignupFirestoreModel {

    String email;

    String token;

    String phone;

    String name;

    public SignupFirestoreModel(String email, String token, String phone, String name) {
        this.email = email;
        this.token = token;
        this.phone = phone;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
