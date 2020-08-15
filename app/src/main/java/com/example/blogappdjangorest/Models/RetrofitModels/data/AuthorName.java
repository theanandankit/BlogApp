package com.example.blogappdjangorest.Models.RetrofitModels.data;

import com.example.blogappdjangorest.Models.RetrofitModels.Nameonlymodel;
import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorName {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("author")
    @Expose
    private ProfileSearchResponse author;

    public ProfileSearchResponse getAuthor() {
        return author;
    }

    public void setAuthor(ProfileSearchResponse author) {
        this.author = author;
    }

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
