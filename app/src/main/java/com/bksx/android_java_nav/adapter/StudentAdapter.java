package com.bksx.android_java_nav.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.activity.ButtonClickListener;
import com.bksx.android_java_nav.databinding.ItemStudentBinding;
import com.bksx.android_java_nav.entity.Student;
import com.bksx.android_java_nav.view.CaseGrivate;
import com.bksx.android_java_nav.view.StuDialog;

import java.util.ArrayList;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-13:22
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private ArrayList<Student> mDatas;
    public Context mContext;
    private OnItemClickListener onItemClickListener;

    public StudentAdapter(ArrayList<Student> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding itemStudentBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_student, parent, false);
        return new MyViewHolder(itemStudentBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (holder != null) {
            ItemStudentBinding itemStudentBinding = DataBindingUtil.getBinding(holder.itemView);
            assert itemStudentBinding != null;
            itemStudentBinding.setStudent(mDatas.get(position));
            itemStudentBinding.setNetWorkImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3933355596,669391437&fm=26&gp=0.jpg");
            itemStudentBinding.setNetWorkImageResourse(R.drawable.ic_launcher_foreground);
            itemStudentBinding.setButtonCickListener(new ButtonClickListener(mContext, position));
            itemStudentBinding.executePendingBindings();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(mDatas.get(position).id);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            this.onItemClickListener = onItemClickListener;
        }
    }
}
