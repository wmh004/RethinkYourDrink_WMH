package com.example.rethinkyourdrink.DB;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.rethinkyourdrink.ActivityClass;

import java.util.List;

public class Repository {
    private DAO dao;
    private LiveData<List<ActivityClass>> summaryList;
    private LiveData<Integer> Day1Data, Day2Data, Day3Data, Total;

    Repository(Application application) {
        RoomDatabases db = RoomDatabases.getDatabase(application);
        dao = db.dao();
        summaryList = dao.getSummary();
        Day1Data = dao.getDataDay1();
        Day2Data = dao.getDataDay2();
        Day3Data = dao.getDataDay3();
        Total = dao.getTotal();
    }

    LiveData<List<ActivityClass>> getSummary() {
        return summaryList;
    }

    LiveData<Integer> getDataDay1() {
        return Day1Data;
    }

    LiveData<Integer> getDataDay2() {
        return Day2Data;
    }

    LiveData<Integer> getDataDay3() {
        return Day3Data;
    }

    LiveData<Integer> getTotal() {
        return Total;
    }

    void insert(ActivityClass activity) {
        RoomDatabases.databaseWriteExecutor.execute(() -> {
            dao.insert(activity);
        });
    }

}
