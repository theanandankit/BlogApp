package com.example.blogappdjangorest.Models.RetrofitModels.follower;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList2follower {

    @SerializedName("who")
    @Expose
    private Who who;

    public Who getWho() {
        return who;
    }

    public void setWho(Who who) {
        this.who = who;
    }

}
