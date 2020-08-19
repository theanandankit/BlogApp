package com.example.blogappdjangorest.Retrofit;

import com.example.blogappdjangorest.Models.RetrofitModels.AddBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.BlogInfoResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.CategoryResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.ChangePasswordResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupInfoResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListMemberResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.GroupListResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.JoinGroupResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.LoginResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.Pagination.HomePagePaginationResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileInfoResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.SignUpResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.StartFollowResponse;
import com.example.blogappdjangorest.Models.RetrofitModels.data.ProfileUser;
import com.example.blogappdjangorest.Models.RetrofitModels.editblog.EditBlogList;
import com.example.blogappdjangorest.Models.RetrofitModels.editblog.Editblogput;
import com.example.blogappdjangorest.Models.RetrofitModels.follower.followerList;
import com.example.blogappdjangorest.Models.RetrofitModels.following.FollowingList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register/")
    @FormUrlEncoded
    Call<SignUpResponse> register(@Field("email") String email, @Field("password") String password, @Field("username") String username, @Field("first_name") String first_name, @Field("last_name") String last_name);

    @GET("public-blog")
    Call<HomePagePaginationResponse> public_blog(@Query("page") String page);

    @GET("full-profile-info/")
    Call<ArrayList<ProfileUser>> profileUser(@Query("user_id") int userid);

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
    Call<AddBlogResponse> add_blog(@Field("url") String url,@Field("title") String title,@Field("body") String body,@Field("category") String category,@Field("author") String author,@Field("status") String status,@Field("group") String group);

    @POST("start-follow/")
    @FormUrlEncoded
    Call<StartFollowResponse> follow(@Field("who") String who,@Field("whom") String whom);

    @POST("join-group/")
    @FormUrlEncoded
    Call<JoinGroupResponse> join_group(@Header("Authorization") String Authorization,@Field("group_code") String group_code);

    @POST("create-group/")
    @FormUrlEncoded
    Call<String> create_group(@Field("group_id") String group_id,@Field("group_description") String group_description,@Field("creator_id") String creator_id,@Field("group_code") String group_code,@Field("url") String url);

    @PUT("change-password/")
    @FormUrlEncoded
    Call<ChangePasswordResponse> change_password(@Header("Authorization") String Authorization,@Field("old_password") String old_password,@Field("new_password") String new_password);

    @GET("follow-list/")
    Call<ArrayList<followerList>> followerlistthing(@Query("id") int id);

    @GET("following-list/")
    Call<ArrayList<FollowingList>> followinglistthing(@Query("id") int id_following);

    @GET("get-user-info/")
    Call<ArrayList<EditBlogList>> EditBlogthing(@Query("user_id") int id_blog);

    @POST("add-user-info/")
    @FormUrlEncoded
    Call<Editblogput> addBlogPut(@Header("Authorization") String Authorization,@Field("user_id") int usr_id,@Field("description") String Descr,@Field("url") String url);

    @GET("group-member-list")
    Call<ArrayList<GroupListMemberResponse>> get_group_member(@Query("user_id") String user_id);

    @POST("check-follow/")
    @FormUrlEncoded
    Call<StartFollowResponse> check_follow(@Field("who") String who,@Field("whom") String whom);

    @GET("get-profile/")
    Call<ProfileInfoResponse> get_profile(@Header("Authorization") String Authentication);

    @GET("group-info/")
    Call<ArrayList<GroupInfoResponse>> group_info(@Query("group_id") String group_id);

    @GET("category-blog")
    Call<HomePagePaginationResponse> initial_blog(@Query("category") String category,@Query("page") String page);

    @POST("unfollow/")
    @FormUrlEncoded
    Call<StartFollowResponse> unfollow(@Field("who") String who,@Field("whom") String whom);

    @PUT("edit-user-info/")
    @FormUrlEncoded
    Call<Editblogput> editUserInfo(@Header("Authorization") String Authorization,@Field("user_id") int usr_id,@Field("description") String Descr,@Field("url") String url);

}