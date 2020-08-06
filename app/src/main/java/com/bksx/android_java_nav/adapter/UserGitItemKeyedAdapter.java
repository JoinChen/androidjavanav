package com.bksx.android_java_nav.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bksx.android_java_nav.R;
import com.bksx.android_java_nav.model.UserGitHub;
import com.squareup.picasso.Picasso;

/**
 * @Author JoneChen
 * @Date 2020\8\5 0005-11:04
 */
public class UserGitItemKeyedAdapter extends PagedListAdapter<UserGitHub, UserGitItemKeyedAdapter.UserGitViewHolder> {
    Context context;

    public UserGitItemKeyedAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static DiffUtil.ItemCallback<UserGitHub> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserGitHub>() {
                @Override
                public boolean areItemsTheSame(@NonNull UserGitHub oldItem, @NonNull UserGitHub newItem) {
                    return oldItem.imageUrl.equals(newItem.imageUrl);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull UserGitHub oldItem, @NonNull UserGitHub newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public UserGitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new UserGitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserGitViewHolder holder, int position) {
        UserGitHub item = getItem(position);
        if (item != null) {
            Picasso.get()
                    .load(item.imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivMovie);
            holder.tvName.setText(item.name);
            holder.tvUrl.setText(item.id + "");
        } else {
            holder.ivMovie.setImageResource(R.drawable.ic_launcher_foreground);
            holder.tvName.setText("");
            holder.tvUrl.setText("");
        }
    }

    class UserGitViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvName;
        TextView tvUrl;
        public UserGitViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvName = itemView.findViewById(R.id.tvDescribe);
            tvUrl = itemView.findViewById(R.id.tvName);
        }
    }
}
