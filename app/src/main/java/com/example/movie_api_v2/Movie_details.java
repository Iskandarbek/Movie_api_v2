package com.example.movie_api_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.zip.Inflater;

public class Movie_details extends AppCompatActivity {
    TextView title, overview;
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);
        title = findViewById(R.id.movieTitle);
        overview = findViewById(R.id.overview);

        String object2 = getIntent().getStringExtra("movie"); // unzipping
        Gson gson = new Gson();
        movie = gson.fromJson(object2, Movie.class); //

        title.setText(movie.getOriginal_title());
        overview.setText(movie.getOverview());



    }
}