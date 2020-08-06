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
import com.squareup.picasso.Picasso;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-15:04
 */
public class MoviePagedListAdapter extends PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder> {
    private Context context;

    public MoviePagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    return oldItem.id.equals(newItem.id);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            Picasso.get()
                    .load(movie.images.small)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivMovie);
            holder.tvName.setText(movie.title);
            holder.tvUrl.setText(movie.year + "");
        }else {
            holder.ivMovie.setImageResource(R.drawable.ic_launcher_foreground);
            holder.tvName.setText("");
            holder.tvUrl.setText("");
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvName;
        TextView tvUrl;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvName = itemView.findViewById(R.id.tvDescribe);
            tvUrl = itemView.findViewById(R.id.tvName);
        }
    }
}
