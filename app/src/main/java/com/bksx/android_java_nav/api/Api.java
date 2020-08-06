package com.bksx.android_java_nav.api;

import com.bksx.android_java_nav.model.Movies;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserGitHub;
import com.bksx.android_java_nav.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-10:01
 */
public interface Api {

    @GET("movie/in_theaters")
    Call<Movies> getMovies(
            @Query("start") int since,
            @Query("count") int perPage,
            @Query("apikey") String key
    );

    @GET("users")
    Call<UserResponse>getUsers(
            @Query("page") int page,
            @Query("pagesize") int pagesize,
            @Query("site") String site
    );

    @GET("users")
    Call<List<UserGitHub>>getUsersGitHub(
            @Query("since") int since,
            @Query("per_page") int per_page
    );
}
