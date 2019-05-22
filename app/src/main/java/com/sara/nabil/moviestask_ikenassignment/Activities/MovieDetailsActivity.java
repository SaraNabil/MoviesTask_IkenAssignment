package com.sara.nabil.moviestask_ikenassignment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sara.nabil.moviestask_ikenassignment.app.Contants;
import com.sara.nabil.moviestask_ikenassignment.Models.MovieModel;
import com.sara.nabil.moviestask_ikenassignment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends AppCompatActivity {

    //initialize views
    @BindView(R.id.movieTitleTv)
    TextView movieTitleTv;
    @BindView(R.id.movieNameTv)
    TextView movieNameTv;
    @BindView(R.id.movieReleaseDateTv)
    TextView movieReleaseDateTv;
    @BindView(R.id.movieOverviewTv)
    TextView movieOverviewTv;
    @BindView(R.id.movieIv)
    ImageView movieIv;

    MovieModel model ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //ButterKnife
        ButterKnife.bind(this);

        model= (MovieModel) getIntent().getSerializableExtra("movieModel");

        movieTitleTv.setText(model.getName());
        movieNameTv.setText(model.getName());
        movieOverviewTv.setText(model.getOverview());
        movieReleaseDateTv.setText(model.getRelease_date());

        Glide.with(this)
                .load(Contants.baseImage+model.getPoster_path())
                .into(movieIv);
    }

    /**
     * back action
     */
    @OnClick(R.id.backBtn)
    void back() {
        finish();
    }
}
