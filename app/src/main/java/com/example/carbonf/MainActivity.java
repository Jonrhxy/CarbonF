package com.example.carbonf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int distance = 5; // Default distance (in km)
    private double totalCarbonFootprint = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        SeekBar seekBar = findViewById(R.id.seekbar_distance);
        TextView tvDistance = findViewById(R.id.tv_distance);
        CheckBox cbTricycle = findViewById(R.id.checkbox_tricycle);
        CheckBox cbJeep = findViewById(R.id.checkbox_jeep);
        CheckBox cbBus = findViewById(R.id.checkbox_bus);
        CheckBox cbCar = findViewById(R.id.checkbox_car);
        CheckBox cbBicycle = findViewById(R.id.checkbox_bicycle);
        Button btnContinue = findViewById(R.id.btn_continue);

        // Update distance value based on SeekBar progress
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distance = progress;
                tvDistance.setText(distance + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });

        // Continue button logic
        btnContinue.setOnClickListener(v -> {
            // Reset the total carbon footprint
            totalCarbonFootprint = 0.0;

            // Calculate carbon footprint for each mode of transportation
            if (cbTricycle.isChecked()) {
                totalCarbonFootprint += distance * 71; // Example: Tricycle emits 71g CO2 per km
            }
            if (cbJeep.isChecked()) {
                totalCarbonFootprint += distance * 150; // Example: Jeep emits 150g CO2 per km
            }
            if (cbBus.isChecked()) {
                totalCarbonFootprint += distance * 90; // Example: Bus emits 90g CO2 per km
            }
            if (cbCar.isChecked()) {
                totalCarbonFootprint += distance * 170; // Example: Car emits 170g CO2 per km
            }
            if (cbBicycle.isChecked()) {
                totalCarbonFootprint += 0; // Bicycles emit 0g CO2 per km
            }

            // Log the calculated total carbon footprint for debugging
            Log.d("MainActivity", "Calculated Total Carbon Footprint: " + totalCarbonFootprint);

            // Navigate to TotalCarbonActivity and pass the total carbon footprint
            Intent intent = new Intent(MainActivity.this, TotalCarbonActivity.class);
            intent.putExtra("total_carbon", totalCarbonFootprint); // Use consistent key
            startActivity(intent);
        });
    }
}
