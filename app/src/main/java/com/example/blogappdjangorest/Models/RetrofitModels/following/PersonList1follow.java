package com.example.blogappdjangorest.Models.RetrofitModels.following;

import com.example.blogappdjangorest.Models.RetrofitModels.ProfileSearchResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList1follow {

    public ProfileSearchResponse getWhom() {
        return whom;
    }

    public void setWhom(ProfileSearchResponse whom) {
        this.whom = whom;
    }

    @SerializedName("whom")
    @Expose
    private ProfileSearchResponse whom;


}
