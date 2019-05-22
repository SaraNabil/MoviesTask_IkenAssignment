package com.sara.nabil.moviestask_ikenassignment.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sara.nabil.moviestask_ikenassignment.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /**
     * open search Activity
     */
    @OnClick(R.id.goToSearchBtn)void openSearchActivity(){
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }
}
