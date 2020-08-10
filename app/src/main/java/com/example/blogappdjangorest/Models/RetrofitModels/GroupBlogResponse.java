package com.example.blogappdjangorest.Models.RetrofitModels;

public class GroupBlogResponse {

    private String date;

    private String group_id;

    private String[] members;

    private String creator_id;

    private Group_related[] groups;

    private String group_description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public Group_related[] getGroup() {
        return groups;
    }

    public void setGroup(Group_related[] groups) {
        this.groups = groups;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }
}
