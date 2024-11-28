package com.example.carbonf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodWasteActivity extends AppCompatActivity {

    private Button btnCalculateWaste;
    private RadioGroup radioGroupWaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_waste);

        // Initialize the views
        btnCalculateWaste = findViewById(R.id.btn_calculate_waste);
        radioGroupWaste = findViewById(R.id.radio_group_food_waste);

        btnCalculateWaste.setOnClickListener(v -> {
            // Get the selected radio button ID
            int selectedId = radioGroupWaste.getCheckedRadioButtonId();

            if (selectedId == -1) {
                // No option selected
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            // Find the selected RadioButton
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedOption = selectedRadioButton.getText().toString();

            // Get the total carbon from previous activity (FoodCarbonActivity)
            double totalCarbon = getIntent().getDoubleExtra("total_carbon", 0.0);

            // Calculate the food waste carbon footprint based on selected option
            double carbonFootprintFromWaste = calculateCarbonFromWaste(selectedOption);

            // Add food waste carbon to the total carbon footprint
            totalCarbon += carbonFootprintFromWaste;

            // Pass the final total carbon footprint to TotalCarbonActivity
            Intent intent = new Intent(FoodWasteActivity.this, TotalCarbonActivity.class);
            intent.putExtra("total_carbon", totalCarbon); // Pass the final total carbon
            startActivity(intent);
        });
    }

    // A sample calculation method based on food waste frequency
    private double calculateCarbonFromWaste(String selectedOption) {
        double carbonEmission = 0;

        switch (selectedOption) {
            case "Never":
                carbonEmission = 0;
                break;
            case "Rarely":
                carbonEmission = 0.5;
                break;
            case "Sometimes":
                carbonEmission = 1.0;
                break;
            case "Often":
                carbonEmission = 1.5;
                break;
            case "Always":
                carbonEmission = 2.0;
                break;
        }

        return carbonEmission;
    }
}
