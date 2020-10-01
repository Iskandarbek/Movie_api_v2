package com.example.movie_api_v2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "voteCount")

    @SerializedName("vote_count")
    private int voteCount;

    @ColumnInfo(name = "posterPath")
    private String poster_path;

    @ColumnInfo(name = "adult")
    private boolean adult;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "releaseDate")
    private String release_date;

    @ColumnInfo(name = "voteAverage")
    private double vote_average;


    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
//    @TypeConverters({Converters.class})
//    @ColumnInfo(name = "genreIds")
//    private List<Integer> genre_ids;

    @ColumnInfo(name = "originalTitle")
    private String original_title;

    @ColumnInfo(name = "originalLanguage")
    private String original_language;




    public void setId(int id) {
        this.id = id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getPoster_path() { // сгенерировали полный адрес постера
        String poster_full_path = Utils.base_poster_url + poster_path;
        return poster_full_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date(){
        return release_date;
    };


//    public List<Integer> getGenre_ids() {
//        return genre_ids;
//    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }



    public String getBackdrop_path() {
        String poster_full_path_bd = Utils.base_poster_url + getBackdrop_path();
        return poster_full_path_bd;
    }



    public int getVoteCount() {
        return voteCount;
    }


}
