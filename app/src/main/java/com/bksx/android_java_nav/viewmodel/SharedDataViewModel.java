package com.bksx.android_java_nav.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-9:28
 */
public class SharedDataViewModel extends ViewModel {

    private MutableLiveData<Integer> progress;

    public SharedDataViewModel() {
        super();
    }

    public LiveData<Integer> getProgress(){
        if (progress == null){
            progress = new MutableLiveData<>();
        }
        return progress;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        progress = null;
    }
}
