package com.example.signlanguageapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Interpreter extends AppCompatActivity {

    private EditText interpreterInput;
    private LinearLayout signContainer;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpreter);

        // Initialize UI components
        interpreterInput = findViewById(R.id.interpreterInput);
        signContainer = findViewById(R.id.signContainer);
        convertButton = findViewById(R.id.convertButton);

        // Set click listener for Convert Button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("InterpreterActivity", "Convert Button Clicked"); // Debugging log
                convertTextToSignLanguage();
            }
        });
    }

    private void convertTextToSignLanguage() {
        String inputText = interpreterInput.getText().toString().trim();

        if (inputText.isEmpty()) {
            interpreterInput.setError("Please enter text!");
            return;
        }

        // Clear previous signs
        signContainer.removeAllViews();

        // Split words by spaces
        String[] words = inputText.split("\\s+");

        for (String word : words) {
            // Create a new row (HorizontalScrollView -> LinearLayout) for each word
            HorizontalScrollView scrollView = new HorizontalScrollView(this);
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (char letter : word.toCharArray()) {
                ImageView imageView = new ImageView(this);

                // Get the corresponding sign image for each letter
                int imageResId = getSignImage(letter);
                if (imageResId != 0) {
                    imageView.setImageResource(imageResId);

                    // Set fixed small size for each image
                    int size = 175; // Adjust size as needed
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
                    params.setMargins(5, 5, 5, 5); // Add spacing
                    imageView.setLayoutParams(params);

                    rowLayout.addView(imageView);
                }
            }

            // Wrap row in scrollView (for horizontal scrolling)
            scrollView.addView(rowLayout);
            signContainer.addView(scrollView);
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
