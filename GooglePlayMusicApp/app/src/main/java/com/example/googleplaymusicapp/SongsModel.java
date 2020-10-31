package com.example.googleplaymusicapp;

public class SongsModel {
    public String resId;
    public String title;
    public String albumName;
    public String artistsName;
    public String duration;
    public String id;

    public SongsModel(String resId, String title, String albumName,
                      String artistsName, String duration, String id) {
        this.resId = resId;
        this.title = title;
        this.albumName = albumName;
        this.artistsName = artistsName;
        this.duration = duration;
        this.id = id;
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

    public String getId() {
        return id;
    }
}
