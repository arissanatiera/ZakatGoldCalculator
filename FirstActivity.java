package com.example.zakatestimator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;

public class FirstActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // Set up the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the hamburger menu in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Zakat Estimator");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black)); // Set title color to black

            // Resize and set the hamburger icon
            setResizedHamburgerIcon(R.drawable.menu_bar, 35, 35);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.nav_profile) {
                    startActivity(new Intent(FirstActivity.this, AboutActivity.class));
                } else if (item.getItemId() == R.id.nav_calculate) {
                    startActivity(new Intent(FirstActivity.this, InstructionsActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Button to calculate Zakat
        Button calculateZakatButton = findViewById(R.id.calculateZakatButton);
        calculateZakatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });
    }

    // Helper method to resize and set the hamburger icon
    private void setResizedHamburgerIcon(int drawableResId, int widthDp, int heightDp) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), drawableResId);
        int widthPx = (int) (widthDp * getResources().getDisplayMetrics().density);
        int heightPx = (int) (heightDp * getResources().getDisplayMetrics().density);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, widthPx, heightPx, false);

        BitmapDrawable resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        getSupportActionBar().setHomeAsUpIndicator(resizedDrawable);
    }

    // Inflate the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

