package com.example.signlanguageapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommonWordsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WordVideoAdapter wordAdapter;
    private List<WordModel> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_words);

        recyclerView = findViewById(R.id.recyclerView);

        // Initialize word list
        wordList = new ArrayList<>();
        wordList.add(new WordModel("Hello", R.raw.hello));
        wordList.add(new WordModel("Thank you", R.raw.thank_you));
        wordList.add(new WordModel("Yes", R.raw.yes));
        wordList.add(new WordModel("No", R.raw.no));
        wordList.add(new WordModel("Please", R.raw.please));
        wordList.add(new WordModel("Sorry", R.raw.sorry));
        wordList.add(new WordModel("Goodbye", R.raw.goodbye));
        wordList.add(new WordModel("Help", R.raw.help));
        wordList.add(new WordModel("Bathroom", R.raw.bathroom));
        wordList.add(new WordModel("Food", R.raw.food));
        wordList.add(new WordModel("Water", R.raw.water));
        wordList.add(new WordModel("Friend", R.raw.friend));
        wordList.add(new WordModel("Love", R.raw.love));

        // Set up RecyclerView with adapter
        wordAdapter = new WordVideoAdapter(this, wordList);
        recyclerView.setAdapter(wordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
