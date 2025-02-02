package com.example.rethinkyourdrink;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rethinkyourdrink.DB.ActivityViewModel;
import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
    private ActivityAdapter adapter;
    private RecyclerView recyclerView;
    private TextView TVDisplayTotal, TVDisplayDay1, TVDisplayDay2, TVDisplayDay3;
    private ActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapter with an empty list
        adapter = new ActivityAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observe the summary list and update the RecyclerView
        viewModel.getSummary().observe(this, activities -> {
            adapter.setActivities(activities); // Update adapter data
        });

        // Initialize TextViews
        TVDisplayTotal = findViewById(R.id.TVDisplayTotal);
        TVDisplayDay1 = findViewById(R.id.TVDisplayDay1);
        TVDisplayDay2 = findViewById(R.id.TVDisplayDay2);
        TVDisplayDay3 = findViewById(R.id.TVDisplayDay3);

        // Observe numerical data and update TextViews
        viewModel.getDataDay1().observe(this, day1 -> {
            TVDisplayDay1.setText("" + day1);
        });

        viewModel.getDataDay2().observe(this, day2 -> {
            TVDisplayDay2.setText("" + day2);
        });

        viewModel.getDataDay3().observe(this, day3 -> {
            TVDisplayDay3.setText("" + day3);
        });

        viewModel.getTotal().observe(this, total -> {
            TVDisplayTotal.setText("" + total);
        });

        // Handle system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}