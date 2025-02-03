package com.example.rethinkyourdrink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.rethinkyourdrink.DB.ActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private Spinner SPSelectDay;
    private TextView TVDisplayEachDay;
    private ActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.TBMainAct);
        setSupportActionBar(toolbar);

        SPSelectDay = findViewById(R.id.SPSelectDay);
        TVDisplayEachDay = findViewById(R.id.TVDisplayEachDay);

        ArrayAdapter<CharSequence> adapterDay = ArrayAdapter.createFromResource(
                this, R.array.DaySlots, android.R.layout.simple_spinner_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPSelectDay.setAdapter(adapterDay);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);

        SPSelectDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDay = adapterView.getItemAtPosition(i).toString();

                // Observe LiveData based on selected day
                switch (selectedDay) {
                    case "Day 1":
                        observeDayData(viewModel.getDataDay1());
                        break;
                    case "Day 2":
                        observeDayData(viewModel.getDataDay2());
                        break;
                    case "Day 3":
                        observeDayData(viewModel.getDataDay3());
                        break;
                    default:
                        TVDisplayEachDay.setText("No data available");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                TVDisplayEachDay.setText("");
            }
        });

        // Navigate to RecordActivity
        Button BtnRecordActivity = findViewById(R.id.BtnRecordActivity);
        BtnRecordActivity.setOnClickListener(view -> {
            Intent record = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(record);
        });

        // Navigate to SummaryActivity
        Button BtnSummary = findViewById(R.id.BtnSummary);
        BtnSummary.setOnClickListener(view -> {
            Intent summary = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(summary);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void observeDayData(LiveData<Integer> liveData) {
        liveData.observe(this, count -> {
            if (count != null) {
                TVDisplayEachDay.setText("" + count);
            } else {
                TVDisplayEachDay.setText("/");
            }
        });
    }
}

