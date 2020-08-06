package com.bksx.android_java_nav.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @Author JoneChen
 * @Date 2020\7\30 0030-13:51
 */
public class SimpleWorker extends Worker {
    private String TAG;

    public SimpleWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TAG = this.getClass().getSimpleName();
    }

    @NonNull
    @Override
    public Result doWork() {
//        Log.e(TAG,"执行程序 dowork");
        String data = getInputData().getString("RoomActivity");
        Data data1 = new Data.Builder().putString("out_data","Task success").build();
        return Result.success(data1);
    }
}
