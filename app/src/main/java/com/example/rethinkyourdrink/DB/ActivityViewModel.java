package com.example.rethinkyourdrink.DB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rethinkyourdrink.ActivityClass;

import java.util.List;

public class ActivityViewModel extends AndroidViewModel {

    // Reference to the repository
    private Repository repo;
    // LiveData objects for different data types
    private LiveData<List<ActivityClass>> summaryList;
    private LiveData<Integer> Day1Data, Day2Data, Day3Data, Total;

    // Constructor: initializes the repository and LiveData
    public ActivityViewModel(Application application) {
        super(application);
        repo = new Repository(application);

        // Initialize LiveData by getting the data from the repository
        summaryList = repo.getSummary();
        Day1Data = repo.getDataDay1();
        Day2Data = repo.getDataDay2();
        Day3Data = repo.getDataDay3();
        Total = repo.getTotal();
    }

    // Getters for LiveData to observe in the UI
    public LiveData<List<ActivityClass>> getSummary() {
        return summaryList;
    }

    public LiveData<Integer> getDataDay1() {
        return Day1Data;
    }

    public LiveData<Integer> getDataDay2() {
        return Day2Data;
    }

    public LiveData<Integer> getDataDay3() {
        return Day3Data;
    }

    public LiveData<Integer> getTotal() {
        return Total;
    }

    // Method to insert data into the database
    public void insert(ActivityClass activity) {
        repo.insert(activity);
    }

    public void update(ActivityClass activity) {
        repo.update(activity);
    }
}
