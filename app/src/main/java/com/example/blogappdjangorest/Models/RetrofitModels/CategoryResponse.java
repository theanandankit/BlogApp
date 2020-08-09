package com.example.blogappdjangorest.Models.RetrofitModels;

public class CategoryResponse {

    String category_name;

    String url;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
