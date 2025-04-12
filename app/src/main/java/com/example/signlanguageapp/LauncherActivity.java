package com.example.signlanguageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);

        if (isFirstLaunch) {
            // First time launching, go to LoginActivity
            startActivity(new Intent(this, LoginActivity.class));

            // Set the flag to false so next time it goes to MainActivity
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
        } else {
            // Not the first launch, go to MainActivity
            startActivity(new Intent(this, MainActivity.class));
        }

        finish(); // close the LauncherActivity
    }
}
