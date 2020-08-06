package com.bksx.android_java_nav.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.bksx.android_java_nav.db.UserGitDataBase;
import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.model.UserGitHub;
import com.bksx.android_java_nav.paging.MovieDataSource;
import com.bksx.android_java_nav.paging.MovieDataSourceFactory;
import com.bksx.android_java_nav.paging.UserGitBoundryCallBack;
import com.bksx.android_java_nav.paging.UserGitDataSource;
import com.bksx.android_java_nav.paging.UserGitDataSourceFactory;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-11:12
 */
public class UserGitViewMolder extends AndroidViewModel {
    public LiveData<PagedList<UserGitHub>> userGitPagedList;

    public UserGitViewMolder(@NonNull Application application) {
        super(application);
        UserGitDataBase userGitDataBase = UserGitDataBase.getInstance(application);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(UserGitDataSource.PER_PAGE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(UserGitDataSource.PER_PAGE * 4)
                .setMaxSize(65536 * UserGitDataSource.PER_PAGE)
                .build();
        //通过LivePagedListBuilder配置pagedList并使LiveData包装pagedList将其暴露给activity
        userGitPagedList = (new LivePagedListBuilder<>(userGitDataBase.userGitHubDao().getUserGithubList(),config))
                .setBoundaryCallback(new UserGitBoundryCallBack(application))
                .build();
    }

}
