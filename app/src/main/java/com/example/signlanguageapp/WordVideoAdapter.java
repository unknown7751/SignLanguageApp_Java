package com.example.signlanguageapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordVideoAdapter extends RecyclerView.Adapter<WordVideoAdapter.WordVideoViewHolder> {

    private final Context context;
    private final String[] words;
    private final int[] videoResIds;

    public WordVideoAdapter(Context context, String[] words, int[] videoResIds) {
        this.context = context;
        this.words = words;
        this.videoResIds = videoResIds;
    }

    @NonNull
    @Override
    public WordVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word_video, parent, false);
        return new WordVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordVideoViewHolder holder, int position) {
        holder.wordText.setText(words[position]);
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + videoResIds[position]);
        holder.videoView.setVideoURI(uri);
        holder.videoView.start();

        // Looping the video
        holder.videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            holder.videoView.start();
        });

        // Toggle pause/resume on tap
        holder.videoView.setOnClickListener(v -> {
            if (holder.videoView.isPlaying()) {
                holder.videoView.pause();
            } else {
                holder.videoView.start();
            }
        });
    }


    @Override
    public int getItemCount() {
        return words.length;
    }

    static class WordVideoViewHolder extends RecyclerView.ViewHolder {
        TextView wordText;
        VideoView videoView;


        public WordVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.wordText);
            videoView = itemView.findViewById(R.id.wordVideo);
        }
    }
}
