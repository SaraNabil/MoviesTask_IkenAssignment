package com.sara.nabil.moviestask_ikenassignment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sara.nabil.moviestask_ikenassignment.app.Contants;
import com.sara.nabil.moviestask_ikenassignment.Adapters.MovieDetailsAdapter;
import com.sara.nabil.moviestask_ikenassignment.Models.MovieModel;
import com.sara.nabil.moviestask_ikenassignment.app.MySingleton;
import com.sara.nabil.moviestask_ikenassignment.Adapters.QueriesAdapter;
import com.sara.nabil.moviestask_ikenassignment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    //initialize the views
    @BindView(R.id.movieNameEt)
    EditText movieNameEt;
    @BindView(R.id.resultRv)
    RecyclerView resultRv;
    @BindView(R.id.noResultTv)
    TextView noResultTv;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.suggestionsLl)
    LinearLayout suggestionsLl;
    @BindView(R.id.queriesRv)
    RecyclerView queriesRv;

    //store the query from the user
    String query = "";

    //initialize the arrayList and the adapter
    ArrayList<MovieModel> resultList;
    MovieDetailsAdapter adapter;

    // queries arrayList and adapter for suggestions
    ArrayList<String> queriesList;
    QueriesAdapter queriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //ButterKnife
        ButterKnife.bind(this);

        resultList = new ArrayList<>();
        adapter = new MovieDetailsAdapter(resultList, this);

        //initialize hte recyclerView layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        resultRv.setLayoutManager(layoutManager);
        resultRv.setAdapter(adapter);

        //for suggestions
        queriesList = new ArrayList<>();
        queriesList.add("Titanic");
        queriesList.add("Les quatre cents coups");
        queriesList.add("The Godfather");
        queriesList.add("White Heat");
        queriesList.add("The Shawshank Redemption");
        queriesAdapter = new QueriesAdapter(queriesList, this);
        LinearLayoutManager queriesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        queriesRv.setLayoutManager(queriesLayoutManager);
        queriesRv.setAdapter(queriesAdapter);

        //display the query in EditText
        String querySug = getIntent().getStringExtra("queryName");
        movieNameEt.setText(querySug);

    }

    /**
     * back action
     */
    @OnClick(R.id.backBtn)
    void back() {
        finish();
    }

    /**
     * do the search
     */
    @OnClick(R.id.searchBtn)
    void search() {
        getMovies();
    }

    /**
     * method to get all the data from the json object
     */
    private void getMovies() {

        loading.setVisibility(View.VISIBLE);

        query = movieNameEt.getText().toString().trim();

        String url = Contants.baseUrl + query + "&page=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
                try {
                    int numOfResults = response.getInt("total_results");

                    if (numOfResults == 0) {
                        noResultTv.setVisibility(View.VISIBLE);
                        resultRv.setVisibility(View.GONE);
                    } else {
                        resultRv.setVisibility(View.VISIBLE);
                        noResultTv.setVisibility(View.GONE);
                        suggestionsLl.setVisibility(View.GONE);

                        parseJsonData(response);
                    }
                    Log.d("google", "response ----->  " + numOfResults);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
                loading.setVisibility(View.GONE);
            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(SearchActivity.this).addToRequestQueue(jsObjRequest);


    }

    /**
     * method to get the data from the json array inside the object array and store it in ArrayList
     *
     * @param response response from json array
     * @throws JSONException
     */
    private void parseJsonData(JSONObject response) throws JSONException {

        JSONArray moviesArray = response.getJSONArray("results");
        resultList.clear();
        for (int i = 0; i < moviesArray.length(); i++) {

            JSONObject object = moviesArray.getJSONObject(i);
            MovieModel model = new MovieModel();

            model.setName(object.getString("title"));
            model.setRelease_date(object.getString("release_date"));
            model.setOverview(object.getString("overview"));
            model.setPoster_path(object.getString("poster_path"));

            resultList.add(model);
        }

        adapter.notifyDataSetChanged();

    }
}
