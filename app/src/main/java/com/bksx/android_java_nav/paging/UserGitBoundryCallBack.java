package com.bksx.android_java_nav.paging;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.bksx.android_java_nav.activity.PagingItemKeyedActivity;
import com.bksx.android_java_nav.api.RetrofitClient;
import com.bksx.android_java_nav.db.UserGitDataBase;
import com.bksx.android_java_nav.http.Constant;
import com.bksx.android_java_nav.model.UserGitHub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-14:13
 */
public class UserGitBoundryCallBack extends PagedList.BoundaryCallback<UserGitHub> {

    private Application application;
    private String TAG = this.getClass().getSimpleName();

    public UserGitBoundryCallBack(Application application) {
        this.application = application;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        RetrofitClient.getInstance(Constant.BaseUserGithub)
                .getApi()
                .getUsersGitHub(0,UserGitDataSource.PER_PAGE)
                .enqueue(new Callback<List<UserGitHub>>() {
                    @Override
                    public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                        if (response.body() != null){
                            inserGitUser(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserGitHub>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull UserGitHub itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull UserGitHub itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        RetrofitClient.getInstance(Constant.BaseUserGithub)
                .getApi()
                .getUsersGitHub(itemAtEnd.id,UserGitDataSource.PER_PAGE)
                .enqueue(new Callback<List<UserGitHub>>() {
                    @Override
                    public void onResponse(Call<List<UserGitHub>> call, Response<List<UserGitHub>> response) {
                        if (response.body() != null){
                            inserGitUser(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserGitHub>> call, Throwable t) {

                    }
                });
    }

    private void inserGitUser(List<UserGitHub> list){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                UserGitDataBase.getInstance(application)
                        .userGitHubDao().insertGitUser(list);
            }
        });
    }

    private void clearGitUser(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                UserGitDataBase.getInstance(application)
                        .userGitHubDao()
                        .clear();
            }
        });
    }
}
