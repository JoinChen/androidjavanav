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
import com.bksx.android_java_nav.model.Movie;
import com.bksx.android_java_nav.model.User;
import com.bksx.android_java_nav.model.UserResponse;
import com.bksx.android_java_nav.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

/**
 * @Author JoneChen
 * @Date 2020\8\4 0004-16:29
 */
public class UserPageKeyedAdapter extends PagedListAdapter<User, UserPageKeyedAdapter.UserViewHolder> {
    private Context context;

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.name.equals(newItem.name);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public UserPageKeyedAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User item = getItem(position);
        if (item != null) {
            Picasso.get()
                    .load(item.image)
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

    class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvName;
        TextView tvUrl;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvName = itemView.findViewById(R.id.tvDescribe);
            tvUrl = itemView.findViewById(R.id.tvName);
        }
    }
}
