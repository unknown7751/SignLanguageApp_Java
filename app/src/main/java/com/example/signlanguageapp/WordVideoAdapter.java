package com.example.signlanguageapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordVideoAdapter extends RecyclerView.Adapter<WordVideoAdapter.WordViewHolder> {
    private final Context context;
    private final List<WordModel> wordList;

    public WordVideoAdapter(Context context, List<WordModel> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word_video, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        WordModel word = wordList.get(position);
        holder.wordText.setText(word.getWord());

        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + word.getVideoResId());
        holder.wordVideo.setVideoURI(uri);
        holder.wordVideo.setMediaController(null);

        holder.wordVideo.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            holder.wordVideo.start();
        });

        holder.wordVideo.setOnClickListener(v -> {
            if (holder.wordVideo.isPlaying()) {
                holder.wordVideo.pause();
            } else {
                holder.wordVideo.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordText;
        VideoView wordVideo;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.wordText);
            wordVideo = itemView.findViewById(R.id.wordVideo);
        }
    }
}
