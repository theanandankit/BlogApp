package com.example.blogappdjangorest.Models.RetrofitModels;

public class Memberinfo
{
    String id;

    GroupListResponse group_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupListResponse getGroup_id() {
        return group_id;
    }

    public void setGroup_id(GroupListResponse group_id) {
        this.group_id = group_id;
    }
}
