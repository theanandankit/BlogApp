package com.example.blogappdjangorest.Models.RetrofitModels.follower;

import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList2follower {

    @SerializedName("who")
    @Expose
    private ProfileSearchResponse who;

    public ProfileSearchResponse getWho() {
        return who;
    }

    public void setWho(ProfileSearchResponse who) {
        this.who = who;
    }
}
