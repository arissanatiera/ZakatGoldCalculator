package com.example.zakatestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions); // Set the layout for this activity

        // Setup the toolbar (title bar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ensure title color is black
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Instructions Calculation");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        }


        // Enable the back button on the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Handle back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate to MainActivity when back button is clicked
            Intent intent = new Intent(InstructionsActivity.this, MainActivity.class);
            startActivity(intent); // Start MainActivity
            finish(); // Close this activity so it's removed from the stack
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

