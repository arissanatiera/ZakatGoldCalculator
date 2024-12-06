package com.example.zakatestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);  // Find the Toolbar by ID
        setSupportActionBar(toolbar);  // Set the Toolbar as the ActionBar

        // Ensure title color is black
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Zakat Calculator");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        }

        // Enable the Up (Back) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Show the back arrow icon

        // Initialize UI components
        EditText edtWeight = findViewById(R.id.edtWeight);
        Spinner spinnerType = findViewById(R.id.spinnerType);
        EditText edtGoldValue = findViewById(R.id.edtGoldValue);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView tvTotalValue = findViewById(R.id.tvTotalValue);
        TextView tvZakatPayable = findViewById(R.id.tvZakatPayable);
        TextView tvTotalZakat = findViewById(R.id.tvTotalZakat);

        // Find the error message TextView
        TextView tvErrorMessage = findViewById(R.id.tvErrorMessage);

        // Populate Spinner with gold type options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.gold_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        // Set input type for EditTexts to only accept numbers (with decimals)
        edtWeight.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edtGoldValue.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // Set OnClickListener for the Calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String weightInput = edtWeight.getText().toString();
                String valueInput = edtGoldValue.getText().toString();
                String type = spinnerType.getSelectedItem().toString();

                // Reset error message visibility
                tvErrorMessage.setVisibility(View.GONE);

                // Validate if any field is empty
                if (weightInput.isEmpty() || valueInput.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate if the inputs are numeric using regex
                if (!weightInput.matches("[0-9]+(\\.[0-9]+)?") || !valueInput.matches("[0-9]+(\\.[0-9]+)?")) {
                    // Show the error message
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    return; // Prevent further calculation
                }

                // Parse the numeric values
                double weight = Double.parseDouble(weightInput);
                double valuePerGram = Double.parseDouble(valueInput);

                // Define uruf threshold based on gold type
                double uruf = type.equals("Keep") ? 85 : 200;

                // Calculate results
                double totalGoldValue = weight * valuePerGram;
                double zakatPayableWeight = Math.max(weight - uruf, 0);
                double zakatPayable = zakatPayableWeight * valuePerGram;
                double totalZakat = zakatPayable * 0.025;

                // Display results
                tvTotalValue.setText("Total Gold Value: RM " + totalGoldValue);
                tvZakatPayable.setText("Zakat Payable Value: RM " + zakatPayable);
                tvTotalZakat.setText("Total Zakat: RM " + totalZakat);
            }
        });
    }

    // Inflate the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handle menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.menu_share) {
            // Handle Share action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my Zakat Estimator app: [GitHub URL]");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        } else if (item.getItemId() == R.id.menu_more) {
            // Handle "More" options here
            Toast.makeText(this, "More options selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            // Handle back button in the toolbar
            onBackPressed();  // Go back to the previous activity (FirstActivity)
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
