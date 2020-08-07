package com.example.blogappdjangorest.Retrofit;

import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register/")
    @FormUrlEncoded
    Call<SignUpResponse> register(@Field("email") String email, @Field("password") String password, @Field("username") String username, @Field("first_name") String first_name, @Field("last_name") String last_name);

}
