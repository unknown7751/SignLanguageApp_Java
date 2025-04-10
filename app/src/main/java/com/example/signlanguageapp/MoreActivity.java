package com.example.signlanguageapp;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MoreActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView videoTitle;
    private Button link1, link2, link3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        videoTitle = findViewById(R.id.videoTitle);
        videoView = findViewById(R.id.emergencyVideo);
        link1 = findViewById(R.id.link1);
        link2 = findViewById(R.id.link2);
        link3 = findViewById(R.id.link3);

        videoTitle.setText("Emergency Sign Language Guide");

        // Set video
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.emergency_sign);
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.start();

        // Set links
        link1.setOnClickListener(v -> openLink("https://www.handspeak.com"));
        link2.setOnClickListener(v -> openLink("https://www.startasl.com"));
        link3.setOnClickListener(v -> openLink("https://www.signlanguage101.com"));
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
