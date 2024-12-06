package com.example.zakatestimator; // Replace with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem; // Import MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about); // Ensure the layout is correct

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ensure title color is black
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile Information");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        }

        // Enable the back button on the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Handle the back button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to FirstActivity
            Intent intent = new Intent(AboutActivity.this, FirstActivity.class);
            startActivity(intent);
            finish(); // Finish the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


