package com.example.movie_api_v2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id == :movieId")
    Movie getMovieById(int movieId);

    @Query("DELETE  FROM movies")
    void deleteAllMovies();

    @Insert
    void insertMovie(Movie movie);
    @Delete
    void deleteMovie(Movie movie);

}
