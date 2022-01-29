package com.example.roomdbex;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Post.class,version = 1)
@TypeConverters(Converters.class)
public abstract class PostsDataBase extends RoomDatabase {

    private static PostsDataBase instance;

    public abstract PostDao postDao();

    public static synchronized  PostsDataBase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    ,PostsDataBase.class,"posts_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
