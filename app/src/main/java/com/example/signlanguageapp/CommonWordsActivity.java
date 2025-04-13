package com.example.signlanguageapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommonWordsActivity extends AppCompatActivity {

    String[] words = {
            "Hello", "Thank you", "Yes", "No", "Please",
            "Sorry", "Goodbye", "Help", "Bathroom", "Food",
            "Water", "Friend", "Love"
    };

    int[] videoResIds = {
            R.raw.hello, R.raw.thank_you, R.raw.yes, R.raw.no, R.raw.please,
            R.raw.sorry, R.raw.goodbye, R.raw.help, R.raw.bathroom, R.raw.food,
            R.raw.water, R.raw.friend, R.raw.love
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_words);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new WordVideoAdapter(this, words, videoResIds));
    }
}
