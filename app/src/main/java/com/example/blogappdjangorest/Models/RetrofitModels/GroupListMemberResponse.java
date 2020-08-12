package com.example.blogappdjangorest.Models.RetrofitModels;

import java.util.ArrayList;

public class GroupListMemberResponse {

    String first_name;

    ArrayList<Memberinfo> member_info;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public ArrayList<Memberinfo> getMemberinfo() {
        return member_info;
    }

    public void setMemberinfo(ArrayList<Memberinfo> memberinfo) {
        this.member_info = memberinfo;
    }

}
