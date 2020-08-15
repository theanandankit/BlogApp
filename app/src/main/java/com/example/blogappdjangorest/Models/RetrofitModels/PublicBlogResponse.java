package com.example.blogappdjangorest.Models.RetrofitModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicBlogResponse {


    @SerializedName("author")
    @Expose
    private ProfileSearchResponse author;

    private String id;

    private String title;

    private String body;

    private String category;

    private String url;

    private String status;

    private String date;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public ProfileSearchResponse getAuthor() {
        return author;
    }

    public void setAuthor(ProfileSearchResponse author) {
        this.author = author;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
