package com.sara.nabil.moviestask_ikenassignment.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sara.nabil.moviestask_ikenassignment.Activities.MovieDetailsActivity;
import com.sara.nabil.moviestask_ikenassignment.app.Contants;
import com.sara.nabil.moviestask_ikenassignment.Models.MovieModel;
import com.sara.nabil.moviestask_ikenassignment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    private List<MovieModel> movies;
    private Context mContext;
//    private String posterId;

    public MovieDetailsAdapter(List<MovieModel> movies, Context mContext) {
        this.movies = movies;
        this.mContext = mContext;
//        this.posterId = posterId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String baseUrl = Contants.baseImage + movies.get(position).getPoster_path();

        holder.movieNameTv.setText(movies.get(position).getName());
        holder.movieReleaseDateTv.setText(movies.get(position).getRelease_date());
        holder.movieOverviewTv.setText(movies.get(position).getOverview());

        Glide.with(mContext)
                .load(baseUrl)
                .into(holder.movieIv);

        holder.movieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("movieModel", movies.get(position));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieNameTv)
        TextView movieNameTv;
        @BindView(R.id.movieReleaseDateTv)
        TextView movieReleaseDateTv;
        @BindView(R.id.movieOverviewTv)
        TextView movieOverviewTv;
        @BindView(R.id.movieIv)
        ImageView movieIv;
        @BindView(R.id.movieItem)
        LinearLayout movieItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
