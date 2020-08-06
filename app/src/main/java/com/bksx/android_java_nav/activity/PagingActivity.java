package com.bksx.android_java_nav.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.adapter.MoviePagedListAdapter;
import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.viewmodel.MovieViewModel;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-15:27
 */
public class PagingActivity extends AppCompatActivity {
    RecyclerView recyMovie;
    MoviePagedListAdapter moviePagedListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        recyMovie = findViewById(R.id.recyMovie);
        moviePagedListAdapter = new MoviePagedListAdapter(this);
        MovieViewModel movieViewModel = new ViewModelProvider(this)
                .get(MovieViewModel.class);
        movieViewModel.moviePagedList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> subjectsBeans) {
                moviePagedListAdapter.submitList(subjectsBeans);
            }
        });
        recyMovie.setLayoutManager(new GridLayoutManager(this,5));
        recyMovie.setAdapter(moviePagedListAdapter);
    }
}
