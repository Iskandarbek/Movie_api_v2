package com.example.movie_api_v2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET ("discover/movie")
    Call<ResponceDiscover> getDiscoverResponce(
    @Query("api_key") String api_key,
    @Query("language") String langauge,
    @Query("sort_by") String sortBy,
    @Query("include_adult") boolean isAdult,
    @Query("include_video") boolean hasVideo
    );



}
