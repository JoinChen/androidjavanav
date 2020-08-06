package com.bksx.android_java_nav.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.adapter.UserGitItemKeyedAdapter;
import com.bksx.android_java_nav.adapter.UserPageKeyedAdapter;
import com.bksx.android_java_nav.db.UserGitDataBase;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserGitHub;
import com.bksx.android_java_nav.viewmodel.UserGitViewMolder;
import com.bksx.android_java_nav.viewmodel.UserViewModel;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-11:01
 */
public class PagingItemKeyedActivity extends AppCompatActivity {
    private RecyclerView recyUserGit;
    public SwipeRefreshLayout srlUserGit;
    private UserGitItemKeyedAdapter userGitItemKeyedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_itemkeyed);
        recyUserGit = findViewById(R.id.recyUserGit);
        srlUserGit = findViewById(R.id.srlUserGit);
        userGitItemKeyedAdapter = new UserGitItemKeyedAdapter(this);
        UserGitViewMolder userGitViewMolder = new ViewModelProvider(this)
                .get(UserGitViewMolder.class);
        userGitViewMolder.userGitPagedList.observe(this, new Observer<PagedList<UserGitHub>>() {
            @Override
            public void onChanged(PagedList<UserGitHub> userGitHubs) {
                userGitItemKeyedAdapter.submitList(userGitHubs);
            }
        });
        recyUserGit.setLayoutManager(new GridLayoutManager(this,5));
        recyUserGit.setAdapter(userGitItemKeyedAdapter);
        srlUserGit.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserGitDataBase.getInstance(PagingItemKeyedActivity.this)
                                .userGitHubDao()
                                .clear();
                        if (srlUserGit.isRefreshing()){
                            srlUserGit.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }
}
