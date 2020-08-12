package com.example.blogappdjangorest.Models.RetrofitModels.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("author_name")
    @Expose
    private List<AuthorName> authorName = null;
    @SerializedName("person_list1")
    @Expose
    private List<PersonList1> personList1 = null;
    @SerializedName("person_list2")
    @Expose
    private List<PersonList2> personList2 = null;
    @SerializedName("user_details")
    @Expose

    private List<UserDetail> userDetails = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AuthorName> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<AuthorName> authorName) {
        this.authorName = authorName;
    }

    public List<PersonList1> getPersonList1() {
        return personList1;
    }

    public void setPersonList1(List<PersonList1> personList1) {
        this.personList1 = personList1;
    }

    public List<PersonList2> getPersonList2() {
        return personList2;
    }

    public void setPersonList2(List<PersonList2> personList2) {
        this.personList2 = personList2;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

}

