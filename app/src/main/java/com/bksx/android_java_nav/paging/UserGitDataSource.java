package com.bksx.android_java_nav.paging;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.bksx.android_java_nav.api.RetrofitClient;
import com.bksx.android_java_nav.http.Constant;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserGitHub;

import java.time.Period;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-10:33
 */
public class UserGitDataSource extends ItemKeyedDataSource<Integer, UserGitHub> {
    public static final int PER_PAGE = 5;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<UserGitHub> callback) {
        RetrofitClient.getInstance(Constant.BaseUserGithub)
                .getApi()
                .getUsersGitHub(0, PER_PAGE)
                .enqueue(new Callback<List<UserGitHub>>() {
                    @Override
                    public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserGitHub>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<UserGitHub> callback) {
        RetrofitClient.getInstance(Constant.BaseUserGithub)
                .getApi()
                .getUsersGitHub(params.key, PER_PAGE)
                .enqueue(new Callback<List<UserGitHub>>() {
                    @Override
                    public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserGitHub>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<UserGitHub> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull UserGitHub item) {
        return item.id;
    }
}
