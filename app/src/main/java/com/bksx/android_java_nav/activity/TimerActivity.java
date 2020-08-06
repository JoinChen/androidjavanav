package com.bksx.android_java_nav.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.viewmodel.TimerViewModel;

/**
 * @Author JoneChen
 * @Date 2020\7\28 0028-14:06
 */
public class TimerActivity extends AppCompatActivity {
    TextView tv_time;
    TextView tv_reset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        tv_time = findViewById(R.id.tv_time);
        tv_reset = findViewById(R.id.tv_reset);
        final TimerViewModel timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        //使用接口方式实现viewModel与activity通信
//        timerViewModel.setOnTimeChangeListener(new TimerViewModel.OnTimerChangeListener() {
//            @Override
//            public void onTimeChange(final int second) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv_time.setText(second + "");
//                    }
//                });
//            }
//        });
        final MutableLiveData<Integer> liveData = (MutableLiveData<Integer>) timerViewModel.getCurrentSecond();
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_time.setText(integer + "");
            }
        });
        timerViewModel.startTiming();

        //通过liveData的setvalue()在ui线程进行数据更新，非ui使用postValue()
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerViewModel.resetCurrentSecond();
            }
        });
    }
}
