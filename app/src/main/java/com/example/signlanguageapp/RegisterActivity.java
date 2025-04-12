package com.example.signlanguageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.signlanguageapp.database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button registerButton;
    ProgressBar loading;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        registerButton = findViewById(R.id.btnRegister);
        loading = findViewById(R.id.loading);
        dbHelper = new DBHelper(this);

        registerButton.setOnClickListener(v -> {
            loading.setVisibility(View.VISIBLE);
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.registerUser(username, password)) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish(); // optional: prevent returning to login on back press

            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }


            loading.setVisibility(View.GONE);
        });
    }
}
