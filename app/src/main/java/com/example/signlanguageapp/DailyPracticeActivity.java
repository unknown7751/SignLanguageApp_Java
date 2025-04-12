package com.example.signlanguageapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DailyPracticeActivity extends AppCompatActivity {

    private ImageView questionImage;
    private EditText answerInput;
    private Button nextButton;
    private TextView questionNumberText, finalScoreText,scoreText;

    private List<Question> allQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private static final String CHANNEL_ID = "quiz_channel";
    private final StringBuilder answerSummary = new StringBuilder(); // Store correct answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_practice);

        // Ask notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        // UI initialization
        questionImage = findViewById(R.id.questionImage);
        answerInput = findViewById(R.id.answerInput);
        nextButton = findViewById(R.id.submitButton);
        scoreText = findViewById(R.id.scoreText);
        questionNumberText = findViewById(R.id.questionNumberText);
        finalScoreText = findViewById(R.id.finalScoreText);

        createNotificationChannel();

        // Load questions
        allQuestions.add(new Question(R.drawable.sign_hello, "hello"));
        allQuestions.add(new Question(R.drawable.sign_thankyou, "thank you"));
        allQuestions.add(new Question(R.drawable.sign_yes, "yes"));
        allQuestions.add(new Question(R.drawable.sign_no, "no"));
        allQuestions.add(new Question(R.drawable.sign_love, "love"));
        allQuestions.add(new Question(R.drawable.sign_good, "good"));
        allQuestions.add(new Question(R.drawable.sign_bad, "bad"));
        allQuestions.add(new Question(R.drawable.sign_please, "please"));
        allQuestions.add(new Question(R.drawable.sign_iloveyou, "i love you"));
        allQuestions.add(new Question(R.drawable.sign_thought, "thought"));

        // Shuffle and pick 5
        Collections.shuffle(allQuestions);
        if (allQuestions.size() > 3) {
            allQuestions = allQuestions.subList(0, 3);
        }

        showQuestion();

        nextButton.setOnClickListener(v -> {
            String userAnswer = answerInput.getText().toString().trim().toLowerCase();
            String correctAnswer = allQuestions.get(currentQuestionIndex).getAnswer().toLowerCase();

            if (userAnswer.equals(correctAnswer)) {
                Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
                score++;
            }
            else{
                Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT).show();
            }

            answerSummary.append("Q").append(currentQuestionIndex + 1)
                    .append(": Correct Answer - ").append(correctAnswer).append("\n");

            currentQuestionIndex++;
            scoreText.setText("Score: " + score + "/3");
            if (currentQuestionIndex < allQuestions.size()) {
                showQuestion();
            } else {
                showFinalScore();
                sendScoreNotification();
            }
        });
    }

    private void showQuestion() {
        Question question = allQuestions.get(currentQuestionIndex);
        questionImage.setImageResource(question.getImageResId());
        answerInput.setText("");
        questionNumberText.setText("Question " + (currentQuestionIndex + 1) + " of " + allQuestions.size());
        finalScoreText.setVisibility(View.GONE);
        questionImage.setVisibility(View.VISIBLE);
        answerInput.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        questionNumberText.setVisibility(View.VISIBLE);
    }

    private void showFinalScore() {
        questionImage.setVisibility(View.GONE);
        answerInput.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        questionNumberText.setVisibility(View.GONE);
        finalScoreText.setVisibility(View.VISIBLE);
        finalScoreText.setText("Your Score: " + score + " / " + allQuestions.size() + "\n\n" + answerSummary.toString());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Quiz Channel";
            String description = "Channel for daily quiz score";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendScoreNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_score) // Make sure this exists in res/drawable
                .setContentTitle("Daily Quiz Completed")
                .setContentText("Your Score: " + score + " / " + allQuestions.size())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return; // Exit if permission not granted
        }

        notificationManager.notify(1, builder.build());
    }

    // Question class
    static class Question {
        private final int imageResId;
        private final String answer;

        public Question(int imageResId, String answer) {
            this.imageResId = imageResId;
            this.answer = answer;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getAnswer() {
            return answer;
        }
    }
}
