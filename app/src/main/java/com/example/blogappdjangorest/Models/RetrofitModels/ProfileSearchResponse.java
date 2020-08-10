package com.example.blogappdjangorest.Models.RetrofitModels;

public class ProfileSearchResponse {
    private User_details[] user_details;

    private String last_name;

    private String id;

    private String first_name;

    private String username;

    public User_details[] getUser_details ()
    {
        return user_details;
    }

    public void setUser_details (User_details[] user_details)
    {
        this.user_details = user_details;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

}
