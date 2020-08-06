package com.bksx.android_java_nav.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.*;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-10:31
 */
@Entity(tableName = "usergithub")
public class UserGitHub {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "imageUrl",typeAffinity = ColumnInfo.TEXT)
    @SerializedName("name")
    public String name;

    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    @SerializedName("avatar_url")
    public String imageUrl;

    public UserGitHub(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
