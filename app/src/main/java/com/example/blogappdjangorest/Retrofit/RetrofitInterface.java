package com.example.blogappdjangorest.Retrofit;

import com.example.blogappdjangorest.Models.RetrofitModels.BlogInfoResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register/")
    @FormUrlEncoded
    Call<SignUpResponse> register(@Field("email") String email, @Field("password") String password, @Field("username") String username, @Field("first_name") String first_name, @Field("last_name") String last_name);

    @GET("public-blog")
    Call<ArrayList<PublicBlogResponse>> public_blog();

    @GET("full-profile-info/")
    Call<ArrayList<ProfileUser>> profileUser(@Query("user_id") int userid);

    @GET("search-blog/")
    Call<ArrayList<PublicBlogResponse>> blogsearch(@Query("search") String search);

    @GET("search-profile/")
    Call<ArrayList<ProfileSearchResponse>> profilesearch(@Query("search") String search);

    @GET("blog-info/")
    Call<ArrayList<BlogInfoResponse>> bloginfo(@Query("id") String id);

}