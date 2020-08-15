package com.example.blogappdjangorest.Models.RetrofitModels.Pagination;

import com.example.blogappdjangorest.Models.RetrofitModels.PublicBlogResponse;

import java.util.ArrayList;

public class HomePagePaginationResponse {

    String count;

    String next;

    String previous;

    ArrayList<PublicBlogResponse> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<PublicBlogResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<PublicBlogResponse> results) {
        this.results = results;
    }
}
