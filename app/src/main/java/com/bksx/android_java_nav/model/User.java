package com.bksx.android_java_nav.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-15:01
 */
public class User {
    @SerializedName("user_id")
    public int id;
    @SerializedName("profile_image")
    public String image;
    @SerializedName("display_name")
    public String name;

    public User(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }


}
