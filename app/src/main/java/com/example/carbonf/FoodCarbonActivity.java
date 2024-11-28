package com.example.carbonf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodCarbonActivity extends AppCompatActivity {

    private TextView tvFoodWeight;
    private SeekBar seekbarFood;
    private Button btnCalculateCarbon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_carbon);

        // Initialize the views
        tvFoodWeight = findViewById(R.id.tv_food_weight);
        seekbarFood = findViewById(R.id.seekbar_food);
        btnCalculateCarbon = findViewById(R.id.btn_calculate_carbon);

        // Retrieve transportation carbon footprint from intent
        double transportationCarbon = getIntent().getDoubleExtra("transportation_carbon", 0.0);

        // Set OnClickListener for the button
        btnCalculateCarbon.setOnClickListener(v -> {
            // Get the value from the seekbar
            int weight = seekbarFood.getProgress();
            // Update the weight TextView
            tvFoodWeight.setText(weight + "kg");

            // Perform calculation for carbon footprint (Example calculation)
            double foodCarbon = calculateCarbonFootprint(weight);

            // Add food carbon to transportation carbon
            double totalCarbon = transportationCarbon + foodCarbon;

            // Pass the total carbon footprint to the next activity (FoodWasteActivity)
            Intent intent = new Intent(FoodCarbonActivity.this, FoodWasteActivity.class);
            intent.putExtra("total_carbon", totalCarbon); // Pass the combined carbon footprint
            startActivity(intent);
        });
    }

    // Example method to calculate carbon footprint based on weight (kg)
    private double calculateCarbonFootprint(int weight) {
        // Example logic: 2.5 kg CO₂ per kg of food
        return weight * 2.5;
    }
}

