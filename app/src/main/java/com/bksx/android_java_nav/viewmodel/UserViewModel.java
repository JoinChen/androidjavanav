package com.bksx.android_java_nav.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserResponse;
import com.bksx.android_java_nav.paging.MovieDataSource;
import com.bksx.android_java_nav.paging.UserDataSource;
import com.bksx.android_java_nav.paging.UserDataSourceFactory;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-16:18
 */
public class UserViewModel extends ViewModel {
    public LiveData<PagedList<User>> pagedListLiveData;

    public UserViewModel() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(UserDataSource.PER_PAGE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(UserDataSource.PER_PAGE * 4)
                .setMaxSize(65536 * UserDataSource.PER_PAGE)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(new UserDataSourceFactory(),config).build();
    }
}
