package com.example.googleplaymusicapp;

public class SongsModel {
    public String resId;
    public String title;
    public String albumName;
    public String artistsName;
    public String duration;

    public SongsModel(String resId, String title, String albumName,
                      String artistsName, String duration) {
        this.resId = resId;
        this.title = title;
        this.albumName = albumName;
        this.artistsName = artistsName;
        this.duration = duration;
    }

    public SongsModel() {
    }

    public String getResId() {
        return resId;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistsName() {
        return artistsName;
    }

    public String getDuration() {
        return duration;
    }
}
