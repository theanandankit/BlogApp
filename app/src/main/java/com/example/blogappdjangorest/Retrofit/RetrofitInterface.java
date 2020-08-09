package com.example.blogappdjangorest.Retrofit;

import com.example.blogappdjangorest.Models.RetrofitModels.AddBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.BlogInfoResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.CategoryResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;

import java.util.ArrayList;

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

    @GET("search-blog/")
    Call<ArrayList<PublicBlogResponse>> blogsearch(@Query("search") String search,@Query("category") String category);

    @GET("search-profile/")
    Call<ArrayList<ProfileSearchResponse>> profilesearch(@Query("search") String search);

    @GET("blog-info/")
    Call<ArrayList<BlogInfoResponse>> bloginfo(@Query("id") String id);

    @GET("get-categories")
    Call<ArrayList<CategoryResponse>> get_categories();

    @GET("group-list/")
    Call<ArrayList<GroupListResponse>> get_group(@Query("user_id") String user_id);

    @GET("group-blog")
    Call<ArrayList<GroupBlogResponse>> get_group_blog(@Query("group_id") String group_id);

    @POST("add-blog/")
    @FormUrlEncoded
    Call<AddBlogResponse> add_blog(@Field("url") String url,@Field("title") String title,@Field("body") String body,@Field("category") String category,@Field("author") String author,@Field("Status") String status,@Field("group") String group);
 }