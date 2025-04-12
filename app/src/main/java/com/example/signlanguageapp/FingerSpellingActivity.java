package com.example.signlanguageapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Locale;

public class FingerSpellingActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private LinearLayout signContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerspelling);
        signContainer = findViewById(R.id.signLayout1);
        Button speakBtn = findViewById(R.id.speakBtn);

        speakBtn.setOnClickListener(v -> startSpeechToText());
    }

    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "Speech not supported", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                convertTextToSignLanguage(result);
            }
        }
    }
    private void convertTextToSignLanguage(ArrayList<String> result) {
        signContainer.removeAllViews();

        // Split the first result (entire sentence) into words
        String[] words = result.get(0).split("\\s+");

        for (String word : words) {
            HorizontalScrollView scrollView = new HorizontalScrollView(this);
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (char letter : word.toCharArray()) {
                ImageView imageView = new ImageView(this);
                int imageResId = getSignImage(letter);
                if (imageResId != 0) {
                    imageView.setImageResource(imageResId);
                    int size = 200;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
                    params.setMargins(5, 5, 5, 5);
                    imageView.setLayoutParams(params);
                    rowLayout.addView(imageView);
                }
            }

            scrollView.addView(rowLayout);
            signContainer.addView(scrollView); // Each word = one row
        }
    }



    // Function to get the corresponding image resource ID for each letter
    private int getSignImage(char letter) {
        letter = Character.toLowerCase(letter);
        String imageName = "sign_" + letter;  // Example: "sign_a", "sign_b", etc.
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        return resId; // Returns the resource ID or 0 if not found
    }

}
