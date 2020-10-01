package com.example.movie_api_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter discoverAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Movie> movieList =  new ArrayList<>();
    private static MovieDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        database = MovieDatabase.getInstance(MainActivity.this);


        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipe_layout);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        loadMovies();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
            }

        });



        Log.i("Tag", "String 51!");

    }
    public void insertMovie (Movie movie){
        new InsertTask().execute(movie);

    }
    private static class InsertTask extends AsyncTask<Movie, Void, Void> {
        @Override
        protected  Void doInBackground(Movie... movies) {
            if (movies != null &&  movies.length > 0){
                database.getMovieDao().insertMovie(movies[0]);
            }
            return null;
        }
    }

    public void loadMovies(){
        swipeRefreshLayout.setRefreshing(true);



        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(Utils.main_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        Log.i("Tag", "String 64!");

        API api = retrofit.create(API.class);


        Call<ResponceDiscover> request = api.getDiscoverResponce(Utils.api_key,Utils.languageEn,Utils.sortBy,false,true);

        request.enqueue(new Callback<ResponceDiscover>() {
            @Override
            public void onResponse(Call<ResponceDiscover> call, Response<ResponceDiscover> response) {
                if (response.isSuccessful()){
                    movieList.clear();
                    movieList.addAll(response.body().getResults());

                    discoverAdapter = new MovieAdapter(movieList,MainActivity.this); // передает в Recycler view
                    recyclerView.setAdapter(discoverAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                    discoverAdapter.setListener(new MovieAdapter.PositionInterface() {
                        @Override
                        public void onMovieClick(int position) {
                            Intent intent = new Intent(MainActivity.this,Movie_details.class);
                            Gson gson = new Gson();
                            String object = gson.toJson(movieList.get(position));
                            intent.putExtra("movie",object);
                            startActivity(intent);
                        }
                        });
                }
            }

            @Override
            public void onFailure(Call<ResponceDiscover> call, Throwable t) {

            }
        });
    }
}