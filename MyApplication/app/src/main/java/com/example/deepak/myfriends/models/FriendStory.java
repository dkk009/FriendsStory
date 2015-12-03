package com.example.deepak.myfriends.models;

import com.example.deepak.myfriends.interfaces.IDataModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 */
public class FriendStory implements IDataModel {
    /*
     "name": "My Friend",
    "photo": "http://static.moralstories.org/wp-content/uploads/2009/05/friends.jpg",
    "phone": "1231231231",
    "email": "android@compassitesinc.com",
    "contact_url": "http://compassitesinc.com",
     */

    @SerializedName("name")
    private String name;
    @SerializedName("photo")
    private String photo;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("contact_url")
    private String contactUrl;
    @SerializedName("our_story")
    ArrayList<OurStory> ourStories;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public ArrayList<OurStory> getOurStories() {
        return ourStories;
    }

    public void setOurStories(ArrayList<OurStory> ourStories) {
        this.ourStories = ourStories;
    }
}
