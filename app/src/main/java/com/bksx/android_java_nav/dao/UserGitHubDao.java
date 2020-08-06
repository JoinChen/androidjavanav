package com.bksx.android_java_nav.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bksx.android_java_nav.model.UserGitHub;

import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-14:02
 */
@Dao
public interface UserGitHubDao {

    @Insert
    void insertGitUser(List<UserGitHub> userGitHubs);

    @Query("DELETE  FROM usergithub")
    void clear();

    @Query("SELECT * FROM usergithub")
    DataSource.Factory<Integer,UserGitHub> getUserGithubList();

}
