package com.bksx.android_java_nav.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author JoneChen
 * @Date 2020\7\28 0028-13:49
 */
public class TimerViewModel extends ViewModel {
    private Timer timer;
    public int currentSeconds;
    //使用LiveData的直接子类MutableLiveData进行包装
    private MutableLiveData<Integer> currentSecond;

    public TimerViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        timer.cancel();
    }

    public void startTiming() {
        getCurrentSecond();
        if (timer == null) {
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSeconds++;
                    currentSecond.postValue(currentSeconds);
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }

    public LiveData<Integer> getCurrentSecond() {
        if (currentSecond == null) {
            currentSecond = new MutableLiveData<>();
        }
        return currentSecond;
    }

    public int resetCurrentSecond() {
        currentSeconds = 0;
        currentSecond.setValue(currentSeconds);
        return currentSeconds;
    }

    public interface OnTimerChangeListener {
        void onTimeChange(int second);
    }

    private OnTimerChangeListener onTimerChangeListener;

    public void setOnTimeChangeListener(OnTimerChangeListener onTimeChangeListener) {
        if (onTimeChangeListener != null) {
            this.onTimerChangeListener = onTimeChangeListener;
        }
    }
}
