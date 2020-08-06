package com.bksx.android_java_nav.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.alibaba.fastjson.JSON;
import com.bksx.android_java_nav.api.RetrofitClient;
import com.bksx.android_java_nav.http.Constant;
import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.model.Movies;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-13:37
 */
public class MovieDataSource extends PositionalDataSource<Movie> {
    public static final int PER_PAGE = 5;
    public static final String APIKEY = "0df993c66c0c636e29ecbb5344252a4a";


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Movie> callback) {
        int startPosition = 0;
        RetrofitClient.getInstance(Constant.BaseUrl)
                .getApi()
                .getMovies(startPosition, PER_PAGE,APIKEY)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(@NotNull Call<Movies> call, @NotNull Response<Movies> response) {
                        Log.e("onResponse", JSON.toJSONString(response.body()));
                        if (response.body()!= null) {
                            callback.onResult(response.body().movieList,
                                    response.body().start,
                                    response.body().total);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Movies> call, @NotNull Throwable t) {
//                        Log.e("Datas",t.toString());
                    }
                });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {
        RetrofitClient.getInstance(Constant.BaseUrl)
                .getApi()
                .getMovies(params.startPosition, PER_PAGE,APIKEY)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(@NotNull Call<Movies> call, @NotNull Response<Movies> response) {
                        if (response.body() != null){
                            callback.onResult(response.body().movieList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Movies> call, @NotNull Throwable t) {

                    }
                });
    }

}
