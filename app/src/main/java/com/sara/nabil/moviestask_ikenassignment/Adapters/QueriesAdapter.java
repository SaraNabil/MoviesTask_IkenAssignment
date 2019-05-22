package com.sara.nabil.moviestask_ikenassignment.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sara.nabil.moviestask_ikenassignment.Activities.SearchActivity;
import com.sara.nabil.moviestask_ikenassignment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueriesAdapter extends RecyclerView.Adapter<QueriesAdapter.ViewHolder> {

    private List<String> queries;
    private Context mContext;

    public QueriesAdapter(List<String> queries, Context mContext) {
        this.queries = queries;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_queries_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.quaryNameTv.setText(queries.get(position));
        holder.queriesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                intent.putExtra("queryName", queries.get(position));
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return queries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.quaryNameTv)
        TextView quaryNameTv;
        @BindView(R.id.queriesItem)
        LinearLayout queriesItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
