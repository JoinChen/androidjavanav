package com.bksx.android_java_nav.activity;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @Author JoneChen
 * @Date 2020\7\30 0030-16:25
 */
public class ButtonClickListener {
    private Context mContext;
    private int mPosition;

    public ButtonClickListener(Context mContext, int position) {
        this.mContext = mContext;
        this.mPosition = position;
    }

    public void onButtonClick(View view) {
        Toast.makeText(mContext, "I am clicked!" + this.mPosition, Toast.LENGTH_SHORT).show();
    }
}
