package com.example.blogappdjangorest.Models.RetrofitModels.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonList1 {

    @SerializedName("whom")
    @Expose
    private Integer whom;

    public Integer getWhom() {
        return whom;
    }

    public void setWhom(Integer whom) {
        this.whom = whom;
    }

}
