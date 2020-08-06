package com.bksx.android_java_nav.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bksx.android_java_nav.db.MyDataBase;
import com.bksx.android_java_nav.entity.Student;

import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-16:28
 * ViewModel中不能使用context可能引起内存泄漏
 */
public class StuViewModel extends AndroidViewModel {
    private MyDataBase myDataBase;
    private LiveData<List<Student>>liveDataStudent;


    public StuViewModel(@NonNull Application application) {
        super(application);
        myDataBase = MyDataBase.getInstance(application);
        liveDataStudent = myDataBase.studentDao().getStudentList();
    }

    public LiveData<List<Student>> getLiveDataStudent(){
        return liveDataStudent;
    }
}
