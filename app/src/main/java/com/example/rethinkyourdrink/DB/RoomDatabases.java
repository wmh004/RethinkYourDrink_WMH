package com.example.rethinkyourdrink.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.rethinkyourdrink.ActivityClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ActivityClass.class}, version = 1, exportSchema = false)
public abstract class RoomDatabases extends RoomDatabase {
    private static volatile RoomDatabases INSTANCE;
    public abstract DAO dao();

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static RoomDatabases getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabases.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDatabases.class, "RethinkYourDrink_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

