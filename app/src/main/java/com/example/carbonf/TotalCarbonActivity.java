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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_total_carbon);

        // Apply window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        TextView tvTotalCarbon = findViewById(R.id.tv_total_carbon);
        Button btnBack = findViewById(R.id.btn_back);

        // Get the total carbon footprint from the intent
        Intent intent = getIntent();
        double totalCarbon = intent.getDoubleExtra("total_carbon", 0.0);

        // Log the retrieved total carbon footprint for debugging
        Log.d(TAG, "Received total carbon footprint: " + totalCarbon);

        // Set the total carbon footprint value in the TextView
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
