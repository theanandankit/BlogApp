package com.example.blogappdjangorest.Models.RetrofitModels.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList2 {

    @SerializedName("who")
    @Expose
    private Integer who;

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

}
