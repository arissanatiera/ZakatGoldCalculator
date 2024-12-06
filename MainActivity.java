package com.example.zakatestimator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText weightInput, valueInput;
    Spinner typeSpinner;
    TextView resultText;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize components
        weightInput = findViewById(R.id.weightInput);
        valueInput = findViewById(R.id.valueInput);
        typeSpinner = findViewById(R.id.typeSpinner);
        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);

        // Button Click Listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateZakat();
            }
        });
    }

    private void calculateZakat() {
        // Reset result display to avoid confusion during errors
        resultText.setText("");

        // Validate inputs
        if (weightInput.getText().toString().trim().isEmpty() ||
                valueInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter all required inputs!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse inputs
            double weight = Double.parseDouble(weightInput.getText().toString().trim());
            double valuePerGram = Double.parseDouble(valueInput.getText().toString().trim());
            String goldType = typeSpinner.getSelectedItem().toString();

            // Validate numerical inputs
            if (weight <= 0 || valuePerGram <= 0) {
                Toast.makeText(this, "Weight and value must be greater than zero!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Determine threshold
            double threshold = goldType.equalsIgnoreCase("Keep") ? 85 : 200;

            // Perform calculations
            double totalValue = weight * valuePerGram;
            double zakatPayableWeight = Math.max(0, weight - threshold);
            double zakatPayableValue = zakatPayableWeight * valuePerGram;
            double totalZakat = zakatPayableValue * 0.025;

            // Display results
            resultText.setText(String.format(
                    "Total Gold Value: RM%.2f\nZakat Payable Value: RM%.2f\nTotal Zakat: RM%.2f",
                    totalValue, zakatPayableValue, totalZakat
            ));

        } catch (NumberFormatException e) {
            // Handle invalid number input
            Toast.makeText(this, "Please enter valid numbers for weight and value!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Catch-all for other unexpected errors
            Toast.makeText(this, "An unexpected error occurred!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
