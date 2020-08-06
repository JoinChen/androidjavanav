package com.bksx.android_java_nav.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.alibaba.fastjson.JSON;
import com.bksx.android_java_nav.api.RetrofitClient;
import com.bksx.android_java_nav.http.Constant;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-15:29
 */
public class UserDataSource extends PageKeyedDataSource<Integer, User> {
    public static final int PER_PAGE = 5;
    public static final int FIRST_PAGE = 1;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {
        RetrofitClient.getInstance(Constant.BaseUrlUser)
                .getApi()
                .getUsers(FIRST_PAGE, PER_PAGE, "stackoverflow")
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.e("stackoverflow", JSON.toJSONString(response.body()));
                        if (response.body() != null) {
                            callback.onResult(response.body().userList, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        RetrofitClient.getInstance(Constant.BaseUrlUser).getApi()
                .getUsers(params.key, PER_PAGE, "stackoverflow")
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null){
                            Integer integer = response.body().hasMore ? params.key + 1 : null;
                            callback.onResult(response.body().userList,integer);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
    }
}
