package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {

    private static final String TAG = "Splash_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the action bar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set the activity to full-screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initialize and animate TextViews
        TextView t1 = findViewById(R.id.tileSplash);
        TextView t2 = findViewById(R.id.smallTitle);

        if (t1 != null && t2 != null) {
            t1.animate().translationX(1000).setDuration(1000).setStartDelay(3000);
            t2.animate().translationX(1000).setDuration(1000).setStartDelay(3000);
        } else {
            Log.e(TAG, "TextView references are null. Ensure that the IDs are correct in activity_splash.xml.");
        }

        // Start a new thread for delayed transition
        new Thread(() -> {
            try {
                Thread.sleep(4000);
                runOnUiThread(() -> {
                    Intent intent = new Intent(Splash_Activity.this, Welcome.class);
                    startActivity(intent);
                    finish(); // Close the splash activity
                });
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted Exception", e);
            }
        }).start();
    }
}
