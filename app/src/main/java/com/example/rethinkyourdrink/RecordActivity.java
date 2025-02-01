package com.example.rethinkyourdrink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecordActivity extends AppCompatActivity {
    private Spinner SPDay, SPBeverage;
    private EditText ETDescription;
    private EditText ETAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record);

        SPDay = findViewById(R.id.SPDay);
        ArrayAdapter<CharSequence> adapterDay = ArrayAdapter.createFromResource(
                this, R.array.DaySlots, android.R.layout.simple_spinner_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPDay.setAdapter(adapterDay);

        SPBeverage = findViewById(R.id.SPBeverage);
        ArrayAdapter<CharSequence> adapterBeverage = ArrayAdapter.createFromResource(
                this, R.array.BeverageSlots, android.R.layout.simple_spinner_item);
        adapterBeverage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPBeverage.setAdapter(adapterBeverage);

        ETDescription = findViewById(R.id.ETDescription);
        ETAmount = findViewById(R.id.ETAmount);

        // Save to ActivityClass
        Button BtnSave = findViewById(R.id.BtnSave);
        BtnSave.setOnClickListener(view -> {
            String selectedDay = SPDay.getSelectedItem().toString();
            String selectedBeverage = SPBeverage.getSelectedItem().toString();
            String amount = ETAmount.getText().toString();
            String description = ETDescription.getText().toString();

            if (amount.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass data back to MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("day", selectedDay);
            resultIntent.putExtra("beverage", selectedBeverage);
            resultIntent.putExtra("amount", amount);
            resultIntent.putExtra("description", description);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
