package com.example.signlanguageapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DailyPracticeActivity extends AppCompatActivity {

    private ImageView questionImage;
    private EditText answerInput;
    private TextView scoreText, finalScoreText;
    private Button submitButton;

    private int currentQuestionIndex = 0;
    private int score = 0;

    // Questions (Images & Answers)
    private int[] questionImages = {
            R.drawable.sign_hello, // Example images
            R.drawable.sign_thankyou,
            R.drawable.sign_love,
            R.drawable.sign_yes,
            R.drawable.sign_no
    };

    private String[] correctAnswers = {"hello", "thank you", "love", "yes", "no"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_practice);

        // Initialize UI components
        questionImage = findViewById(R.id.questionImage);
        answerInput = findViewById(R.id.answerInput);
        scoreText = findViewById(R.id.scoreText);
        finalScoreText = findViewById(R.id.finalScoreText);
        submitButton = findViewById(R.id.submitButton);

        // Load the first question
        loadNextQuestion();

        // Button Click Listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questionImages.length) {
            questionImage.setImageResource(questionImages[currentQuestionIndex]);
            answerInput.setText(""); // Clear input field
        } else {
            // Quiz finished, show final score
            showFinalScore();
        }
    }

    private void checkAnswer() {
        String userAnswer = answerInput.getText().toString().trim().toLowerCase();
        if (userAnswer.isEmpty()) {
            Toast.makeText(this, "Please enter an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check answer
        if (userAnswer.equals(correctAnswers[currentQuestionIndex])) {
            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
            score++;
        }
        else{
            Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT).show();
        }

        // Update Score
        scoreText.setText("Score: " + score + "/5");

        // Move to next question
        currentQuestionIndex++;
        loadNextQuestion();
    }

    private void showFinalScore() {
        finalScoreText.setText("Final Score: " + score + "/5");
        finalScoreText.setVisibility(View.VISIBLE); // Show final score
        submitButton.setEnabled(false); // Disable submit button after quiz
        Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_LONG).show();
    }
}
