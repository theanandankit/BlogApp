package com.example.blogappdjangorest.Models.RetrofitModels;

public class GroupInfoResponse {

    String group_id;

    String group_description;

    Nameonlymodel creator_id;

    String date;

    String url;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public Nameonlymodel getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Nameonlymodel creator_id) {
        this.creator_id = creator_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
