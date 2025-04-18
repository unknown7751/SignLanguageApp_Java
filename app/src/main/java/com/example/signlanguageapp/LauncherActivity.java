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
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false); // default is false

        if (isLoggedIn) {
            // User already logged in, go to MainActivity
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // User not logged in yet, go to LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish(); // close LauncherActivity
    }
}

