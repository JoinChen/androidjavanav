package com.bksx.android_java_nav.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.model.Movies;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-14:47
 * 通过MovieDataSourceFactory
 * 将MovieDataSource包装成LiveDate数据并将其暴露给ViewModel
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieDataSource> liveDataSource = new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSource dataSource = new MovieDataSource();
        liveDataSource.postValue(dataSource);
        return dataSource;
    }
}
