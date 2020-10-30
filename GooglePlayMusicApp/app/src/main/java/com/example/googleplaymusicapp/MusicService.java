package com.example.googleplaymusicapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import static com.example.googleplaymusicapp.MainActivity.songsModels;

public class MusicService extends Service {
    public MusicServiceBinder  musicServiceBinder = new MusicServiceBinder();
    MediaPlayer mediaPlayer;
    ArrayList<SongsModel> songsModelsList = new ArrayList<>();
    Uri uri;
    int position = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        songsModelsList = songsModels;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MusicService", "onBind");
        return musicServiceBinder;
    }

    public class MusicServiceBinder extends Binder {
        MusicService getMusicService()  {
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int newPosition = intent.getIntExtra("servicePosition", -1);
        if(newPosition != -1) {
            playMedia(newPosition);
        }
        return START_STICKY;
    }

    public void playMedia(int startPosition) {
        songsModelsList = songsModels;
       position = startPosition;
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();

            if(songsModelsList != null) {
                createMediaPlayer(startPosition);
                mediaPlayer.start();
            }
        } else {
            createMediaPlayer(startPosition);
            mediaPlayer.start();
        }
    }

    void start() {
        mediaPlayer.start();
    }
    boolean isPlaying() {
            return mediaPlayer.isPlaying();
    }

    void stop() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    void release() {
        mediaPlayer.release();
    }

    int getDuration() {
        return mediaPlayer.getDuration();
    }

    void seekTo(int position) {
        mediaPlayer.seekTo(position);
    }

    int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }
    void createMediaPlayer(int position) {
        uri = Uri.parse(songsModelsList.get(position).getResId());
        mediaPlayer = MediaPlayer.create(getBaseContext(), uri);
    }

    void pause() {
            mediaPlayer.pause();
    }
}
