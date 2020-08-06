package com.bksx.android_java_nav.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.bksx.android_java_nav.model.UserGitHub;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-10:57
 */
public class UserGitDataSourceFactory extends MovieDataSource.Factory<Integer, UserGitHub> {
    MutableLiveData<UserGitDataSource> liveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, UserGitHub> create() {
        UserGitDataSource dataSource = new UserGitDataSource();
        liveData.postValue(dataSource);
        return dataSource;
    }
}
