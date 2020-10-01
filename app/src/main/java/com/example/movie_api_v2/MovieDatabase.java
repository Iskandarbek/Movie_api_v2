package com.example.movie_api_v2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.net.ContentHandler;

@Database(entities = {Movie.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DB_NAME = "movies.db";
    private static MovieDatabase database;

    public static MovieDatabase getInstance(Context context){
        if (database == null) {
            database = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).build();
        }
        return database;
    }
    public abstract MovieDAO getMovieDao();
}
