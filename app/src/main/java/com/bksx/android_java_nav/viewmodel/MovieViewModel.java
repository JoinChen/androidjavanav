package com.bksx.android_java_nav.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.paging.MovieDataSource;
import com.bksx.android_java_nav.paging.MovieDataSourceFactory;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-14:53
 */
public class MovieViewModel extends ViewModel {
    public LiveData<PagedList<Movie>> moviePagedList;

    public MovieViewModel() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(MovieDataSource.PER_PAGE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(MovieDataSource.PER_PAGE * 4)
                .setMaxSize(65536 * MovieDataSource.PER_PAGE)
                .build();
        //通过LivePagedListBuilder配置pagedList并使LiveData包装pagedList将其暴露给activity
        moviePagedList = (new LivePagedListBuilder<>(new MovieDataSourceFactory(),config)).build();

    }

}
