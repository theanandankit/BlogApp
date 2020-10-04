package com.example.blogappdjangorest.Models.RetrofitModels;

public class Author {
    private String is_active;

    private String[] user_permissions;

    private String is_staff;

    private String last_login;

    private String last_name;

    private String[] groups;

    private String password;

    private String id;

    private String date_joined;

    private String first_name;

    private String email;

    private String username;

    public User_details[] getUser_details() {
        return user_details;
    }

    public void setUser_details(User_details[] user_details) {
        this.user_details = user_details;
    }

    private User_details[] user_details;

    public String getIs_active ()
    {
        return is_active;
    }

    public void setIs_active (String is_active)
    {
        this.is_active = is_active;
    }

    public String[] getUser_permissions ()
    {
        return user_permissions;
    }

    public void setUser_permissions (String[] user_permissions)
    {
        this.user_permissions = user_permissions;
    }

    public String getIs_staff ()
    {
        return is_staff;
    }

    public void setIs_staff (String is_staff)
    {
        this.is_staff = is_staff;
    }

    public String getLast_login ()
    {
        return last_login;
    }

    public void setLast_login (String last_login)
    {
        this.last_login = last_login;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String[] getGroups ()
    {
        return groups;
    }

    public void setGroups (String[] groups)
    {
        this.groups = groups;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDate_joined ()
    {
        return date_joined;
    }

    public void setDate_joined (String date_joined)
    {
        this.date_joined = date_joined;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
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
