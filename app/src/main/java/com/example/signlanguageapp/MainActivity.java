package com.example.signlanguageapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate to Interpreter Activity
        Button interpreterButton = findViewById(R.id.interpreter);
        interpreterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Interpreter.class);
            startActivity(intent);
        });

        // Navigate to Daily Practice
        Button dailyPracticeButton = findViewById(R.id.dailyPractice);
        dailyPracticeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DailyPracticeActivity.class);
            startActivity(intent);
        });

        // Open "Sign Language and Deaf Culture" article
        Button moreB1 = findViewById(R.id.moreB1);
        moreB1.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Deaf_culture"));
            startActivity(browserIntent);
        });

        // Open "Sign Language around the World" article
        Button moreB2 = findViewById(R.id.moreB2);
        moreB2.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/List_of_sign_languages"));
            startActivity(browserIntent);
        });

        Button fingerSpellingButton = findViewById(R.id.fingerspelling);
        fingerSpellingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FingerSpellingActivity.class);
                startActivity(intent);
            }
        });
        Button moreButton = findViewById(R.id.moreB3);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intent);
            }
        });


    }
}
