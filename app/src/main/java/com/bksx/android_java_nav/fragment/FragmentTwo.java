package com.bksx.android_java_nav.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.viewmodel.SharedDataViewModel;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-9:39
 */
public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragtwo,container,false);
        final SeekBar seekBar = view.findViewById(R.id.seekbar_two);
        SharedDataViewModel sharedDataViewModel = new ViewModelProvider(getActivity()).get(SharedDataViewModel.class);
        final MutableLiveData<Integer> liveData = (MutableLiveData<Integer>) sharedDataViewModel.getProgress();
        liveData.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                seekBar.setProgress(integer);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                liveData.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }
}
