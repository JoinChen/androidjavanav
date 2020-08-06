package com.bksx.android_java_nav.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bksx.android_java_nav.dao.UserGitHubDao;
import com.bksx.android_java_nav.model.UserGitHub;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-13:49
 */
@Database(entities = {UserGitHub.class}, version = 1, exportSchema = false)
public abstract class UserGitDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "usergithub_db";
    private static UserGitDataBase userGitDataBase;

    public static synchronized UserGitDataBase getInstance(Context context) {
        if (userGitDataBase == null) {
            userGitDataBase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserGitDataBase.class,
                    DATABASE_NAME)
                    .build();
        }
        return userGitDataBase;
    }

    public abstract UserGitHubDao userGitHubDao();
}
