package com.bksx.android_java_nav.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-15:02
 */
public class UserResponse {
    @SerializedName("items")
    public List<User> userList;

    @SerializedName("has_more")
    public boolean hasMore;
}
