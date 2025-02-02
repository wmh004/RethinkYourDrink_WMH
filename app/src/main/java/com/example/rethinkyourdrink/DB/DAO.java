package com.example.rethinkyourdrink.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rethinkyourdrink.ActivityClass;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ActivityClass activty);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ActivityClass activty);

    @Query("SELECT * FROM ActivityClass")
    LiveData<List<ActivityClass>> getSummary();

    @Query("SELECT COUNT(*) FROM ActivityClass WHERE Day='Day 1'")
    LiveData<Integer> getDataDay1();

    @Query("SELECT COUNT(*) FROM ActivityClass WHERE Day='Day 2'")
    LiveData<Integer> getDataDay2();

    @Query("SELECT COUNT(*) FROM ActivityClass WHERE Day='Day 3'")
    LiveData<Integer> getDataDay3();

    @Query("SELECT COUNT(*) FROM ActivityClass")
    LiveData<Integer> getTotal();
}