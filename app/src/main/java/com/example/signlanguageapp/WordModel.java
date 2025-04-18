
package com.example.signlanguageapp;
public class WordModel {
    private String word;
    private int videoResId;

    public WordModel(String word, int videoResId) {
        this.word = word;
        this.videoResId = videoResId;
    }

    public String getWord() {
        return word;
    }

    public int getVideoResId() {
        return videoResId;
    }
}
