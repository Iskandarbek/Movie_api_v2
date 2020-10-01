package com.example.movie_api_v2;

import androidx.room.TypeConverter;

import java.util.List;

public class Converters {

    @TypeConverter
    public static String makeString(List<Integer> genreIds){
        String ids = "";
        for (Integer integer: genreIds){
            ids += String.valueOf(integer);
        }
        return ids;
    }

}
