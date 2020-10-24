package com.example.googleplaymusicapp;

public class SongsModel {
    public int resId;
    public String title;
    public String songName;
    public String artistsName;
    public int duration;

    public SongsModel(int resId, String title, String songName,
                      String artistsName, int duration) {
        this.resId = resId;
        this.title = title;
        this.songName = songName;
        this.artistsName = artistsName;
        this.duration = duration;
    }

    public int getResId() {
        return resId;
    }

    public String getTitle() {
        return title;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistsName() {
        return artistsName;
    }
}
