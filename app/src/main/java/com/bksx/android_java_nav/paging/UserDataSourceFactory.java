package com.bksx.android_java_nav.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserResponse;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-16:10
 */
public class UserDataSourceFactory extends DataSource.Factory<Integer, User> {
    MutableLiveData<UserDataSource> liveData = new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Integer, User> create() {
        UserDataSource userDataSource = new UserDataSource();
        liveData.postValue(userDataSource);
        return userDataSource;
    }
}
