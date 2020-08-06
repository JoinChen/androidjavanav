package com.bksx.android_java_nav.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.adapter.StudentAdapter;
import com.bksx.android_java_nav.databinding.ActivityRoomBinding;
import com.bksx.android_java_nav.db.MyDataBase;
import com.bksx.android_java_nav.entity.Student;
import com.bksx.android_java_nav.model.NameModel;
import com.bksx.android_java_nav.view.CaseGrivate;
import com.bksx.android_java_nav.view.StuDialog;
import com.bksx.android_java_nav.viewmodel.StuViewModel;
import com.bksx.android_java_nav.viewmodel.TwoWayBindingViewModel;
import com.bksx.android_java_nav.worker.SimpleWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-13:17
 */
public class RoomActivity extends AppCompatActivity{
    private RecyclerView rvStudent;
    private Button btnAddStu;
    private ArrayList<Student> stuDatas;
    private StudentAdapter adapter;
    private MyDataBase myDataBase;
    private int positionId;//
    private int clickPosition;
    private String TAG;
    private String mAction = "";
    private QueryStudentTask task;
    private StuViewModel stuViewModel;
    private Button btnDoWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用databinding无需使用findViewById
        TAG = this.getClass().getSimpleName();
        ActivityRoomBinding activityRoomBinding = DataBindingUtil.setContentView(this, R.layout.activity_room);
        activityRoomBinding.setViewModel(new TwoWayBindingViewModel());
        //使用viewModel观察者模式
        stuViewModel = new ViewModelProvider(this).get(StuViewModel.class);
        //Room数据库
        myDataBase = MyDataBase.getInstance(this);

        //recycler适配数据
        stuDatas = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        activityRoomBinding.rvStudent.setLayoutManager(gridLayoutManager);
        adapter = new StudentAdapter(stuDatas, this);
        activityRoomBinding.rvStudent.setAdapter(adapter);
        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickPosition = position;
                final StuDialog stuDialog = new StuDialog(RoomActivity.this, R.style.dialog);
                stuDialog.setmGrivate(CaseGrivate.CENTER)
                        .setScreenPercent(0.3f, 0.2f);
                stuDialog.show();
                stuDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mAction = stuDialog.action;
                        task = new QueryStudentTask();
                        task.execute();
                    }
                });
            }
        });

        //使用观察者模式对数据进行监听
        stuViewModel.getLiveDataStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                stuDatas.clear();
                stuDatas.addAll(students);
                adapter.notifyDataSetChanged();
                if (mAction.equals("")) {
                    if (stuDatas != null && stuDatas.size() > 0) {
                        activityRoomBinding.rvStudent.scrollToPosition(stuDatas.size() - 1);
                    }
                }
            }
        });
        //添加一条学生信息
        activityRoomBinding.btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAction = "";
                task = new QueryStudentTask();
                task.execute();
            }
        });
        //workManager的启动
        activityRoomBinding.btnDoWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();
                Data data = new Data.Builder()
                        .putString(TAG, "success")
                        .build();
//                PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(SimpleWorker.class,15,TimeUnit.MINUTES)
//                        .addTag("simple")
//                        .setConstraints(constraints)
//                        .setInputData(data)
//                        .build();
                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SimpleWorker.class)
//                        .setInitialDelay(10, TimeUnit.SECONDS)
                        .addTag("simple")
                        .setConstraints(constraints)
                        .setInputData(data)
                        .build();
                WorkManager.getInstance(RoomActivity.this)
                        .enqueue(request);
                WorkManager.getInstance(RoomActivity.this).getWorkInfoByIdLiveData(request.getId())
                        .observe(RoomActivity.this, new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                    String out_data = workInfo.getOutputData().getString("out_data");
                                    Log.e(TAG, out_data);
                                }
                            }
                        });
            }
        });

        activityRoomBinding.btnToPaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this,PagingActivity.class);
                startActivity(intent);
            }
        });

        activityRoomBinding.btnToPagingPaged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this,PagingPageKeyedActivity.class);
                startActivity(intent);
            }
        });

        activityRoomBinding.btnToPagingItemKeyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this,PagingItemKeyedActivity.class);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private class QueryStudentTask extends AsyncTask<Void, Void, Void> {

        public QueryStudentTask() {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (mAction != null) {
                if (mAction.equals("update")) {
                    myDataBase.studentDao().updateStudent(new Student(clickPosition, 20 + "", "James" + (clickPosition)));
                } else if (mAction.equals("delete")) {
                    myDataBase.studentDao().deleteStudent(new Student(clickPosition));
                } else {
                    positionId++;
                    myDataBase.studentDao().insertStudent(new Student(0, 35 + "", "Kobe Brant", 111));
                }
            }
            return null;
        }
    }

}
