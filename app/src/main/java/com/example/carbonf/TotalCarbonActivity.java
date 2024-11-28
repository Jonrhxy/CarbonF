package com.example.carbonf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TotalCarbonActivity extends AppCompatActivity {

    private static final String TAG = "TotalCarbonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge functionality
        setContentView(R.layout.activity_total_carbon);

        // Initialize UI elements
        TextView tvTotalCarbon = findViewById(R.id.tv_total_carbon);
        Button btnBack = findViewById(R.id.btn_back);

        // Retrieve the total carbon footprint passed from FoodWasteActivity
        double totalCarbon = getIntent().getDoubleExtra("total_carbon", 0.0);

        // Log the received total carbon footprint for debugging
        Log.d(TAG, "Received total carbon footprint: " + totalCarbon);

        // Display the total carbon footprint in the TextView
        tvTotalCarbon.setText(String.format("%.2f g CO₂", totalCarbon));

        // Back button click listener
        btnBack.setOnClickListener(v -> {
            // Navigate back to the MainActivity
            Intent backIntent = new Intent(TotalCarbonActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish();
        });
    }
}

