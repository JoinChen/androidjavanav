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
import com.bksx.android_java_nav.adapter.UserPageKeyedAdapter;
import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserResponse;
import com.bksx.android_java_nav.viewmodel.MovieViewModel;
import com.bksx.android_java_nav.viewmodel.UserViewModel;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-16:46
 */
public class PagingPageKeyedActivity extends AppCompatActivity {
    RecyclerView recyUser;
    UserPageKeyedAdapter userPageKeyedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_pagedkeyed);
        recyUser = findViewById(R.id.recyUser);
        userPageKeyedAdapter = new UserPageKeyedAdapter(this);
        UserViewModel userViewModel = new ViewModelProvider(this)
                .get(UserViewModel.class);
        userViewModel.pagedListLiveData.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> subjectsBeans) {
                userPageKeyedAdapter.submitList(subjectsBeans);
            }
        });
        recyUser.setLayoutManager(new GridLayoutManager(this,5));
        recyUser.setAdapter(userPageKeyedAdapter);
    }
}
