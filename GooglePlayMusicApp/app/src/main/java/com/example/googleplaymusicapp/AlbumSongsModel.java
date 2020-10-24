package com.example.googleplaymusicapp;

public class AlbumSongsModel {
    public int resIdImg;
    public String songTitle;

    public AlbumSongsModel(int resIdImg, String songTitle) {
        this.resIdImg = resIdImg;
        this.songTitle = songTitle;
    }

    public int getResIdImg() {
        return resIdImg;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
