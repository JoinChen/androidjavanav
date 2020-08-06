package com.bksx.android_java_nav.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bksx.android_java_nav.R;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-14:22
 */
public class StuDialog extends BaseDialog {
    TextView tvUpdate;
    TextView tvDelete;
    public String action;

    public StuDialog(Context context) {
        super(context);
    }

    public StuDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void listen() {
        tvUpdate = findViewById(R.id.tvUpdate);
        tvDelete = findViewById(R.id.tvDelete);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = "update";
                dismiss();
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = "delete";
                dismiss();
            }
        });
    }

    @Override
    public int setLayoutId() {
        return R.layout.item_stu_dialog;
    }

}
