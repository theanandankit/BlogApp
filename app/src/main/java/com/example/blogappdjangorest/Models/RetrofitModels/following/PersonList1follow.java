package com.example.blogappdjangorest.Models.RetrofitModels.following;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList1follow {

    @SerializedName("whom")
    @Expose
    private Whom whom;

    public Whom getWhom() {
        return whom;
    }

    public void setWhom(Whom whom) {
        this.whom = whom;
    }

}
