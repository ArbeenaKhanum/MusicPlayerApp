package com.example.googleplaymusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.googleplaymusicapp.MainActivity.songsModels;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView mTvSongOnPlayName;
    private TextView mTvOnPlayArtistName;
    private TextView mTvDurationStart;
    private TextView mTvTotalDuration;
    private ImageButton mBtnNext;
    private ImageButton mBtnPrevious;
    private ImageButton mBtnShuffle;
    private ImageButton mBtnRepeat;
    private ImageButton mBtnPlayPause;
    private SeekBar mSeekBar;
    private ImageView mIvSongOnPlayImg;
    private ImageView mIvSongPlayIcon;
    private int position = -1;
    static ArrayList<SongsModel> modelSongsList;
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler mHandler = new Handler();
    private Thread playThread;
    private Thread pauseThread;
    private Thread previousThread;
    private Thread nextThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        initViews();
        getIntentOnClick();
        mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
        mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    mSeekBar.setProgress(currentPosition);
                    mTvDurationStart.setText(durationFormat(currentPosition));
                }

                mHandler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        playThreadButton();
        previousThreadButton();
        nextThreadButton();
        super.onResume();
    }

    private void nextThreadButton() {
        nextThread = new Thread() {
            @Override
            public void run() {
                super.run();
                mBtnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextBtnClicked();
                    }
                });
            }
        };

        nextThread.start();
    }

    private void nextBtnClicked() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            position = (position + 1) % modelSongsList.size();
            uri = Uri.parse(modelSongsList.get(position).getResId());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            mediaPlayer.start();
        } else {
            mediaPlayer.pause();
            mediaPlayer.release();
            position = (position + 1) % modelSongsList.size();
            uri = Uri.parse(modelSongsList.get(position).getResId());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_play_songs_icon);
        }
    }

    private void previousThreadButton() {
        previousThread = new Thread() {
            @Override
            public void run() {
                super.run();
                mBtnPrevious.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        previousBtnClicked();
                    }
                });
            }
        };

        previousThread.start();
    }

    private void previousBtnClicked() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();

            position = ((position - 1) <= 0 ? (modelSongsList.size() - 1) : (position - 1));
            uri = Uri.parse(modelSongsList.get(position).getResId());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            mediaPlayer.start();

        } else {
            mediaPlayer.pause();
            mediaPlayer.release();
            position = ((position - 1) <= 0 ? (modelSongsList.size() - 1) : (position - 1));
            uri = Uri.parse(modelSongsList.get(position).getResId());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_play_songs_icon);
        }

    }

    private void playThreadButton() {
        playThread = new Thread() {
            @Override
            public void run() {
                super.run();
                mBtnPlayPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseBtnClicked();
                    }
                });
            }
        };

        playThread.start();
    }

    private void playPauseBtnClicked() {
        if (mediaPlayer.isPlaying()) {
            mBtnPlayPause.setImageResource(R.drawable.ic_play_songs_icon);
            mediaPlayer.pause();
            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });
        } else {
            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            mediaPlayer.start();
            mSeekBar.setMax(mediaPlayer.getDuration() / 1000);

            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });
        }
    }

    private String durationFormat(int currentPosition) {
        String totalTime = "";
        String newTime = "";
        String seconds = String.valueOf(currentPosition % 60);
        String minutes = String.valueOf(currentPosition / 60);
        totalTime = minutes + ":" + seconds;
        newTime = minutes + ":" + "0" + seconds;

        if (seconds.length() == 1) {
            return newTime;
        } else {
            return totalTime;
        }
    }

    private void getIntentOnClick() {
        position = getIntent().getIntExtra("position", -1);
        modelSongsList = songsModels;

        if (modelSongsList != null) {
            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            uri = Uri.parse(modelSongsList.get(position).getResId());
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }

        mSeekBar.setMax(mediaPlayer.getDuration() / 1000);
        metaData(uri);
    }

    private void initViews() {
        mTvSongOnPlayName = findViewById(R.id.tvPlayerSongName);
        mTvOnPlayArtistName = findViewById(R.id.tvPlayerArtistName);
        mTvDurationStart = findViewById(R.id.tvDurationStart);
        mTvTotalDuration = findViewById(R.id.tvDurationTotal);
        mBtnNext = findViewById(R.id.iBtnNextSong);
        mBtnPrevious = findViewById(R.id.iBtnPrevSong);
        mBtnShuffle = findViewById(R.id.iBtnShuffleSong);
        mBtnRepeat = findViewById(R.id.iBtnRepeatSong);
        mBtnPlayPause = findViewById(R.id.playPauseButton);
        mSeekBar = findViewById(R.id.seekBar);
        mIvSongOnPlayImg = findViewById(R.id.ivSongCardImg);
        mIvSongPlayIcon = findViewById(R.id.ivSongPlayImage);
    }

    private void metaData(Uri data) {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(data.toString());
        int durationTotal = Integer.parseInt(modelSongsList.get(position).getDuration()) / 1000;
        mTvTotalDuration.setText(durationFormat(durationTotal));

        byte[] img = metadataRetriever.getEmbeddedPicture();
        if (img != null) {
            Glide.with(this).load(img).into(mIvSongOnPlayImg);
            Glide.with(this).load(img).into(mIvSongPlayIcon);
        } else {
            Glide.with(this).load(R.drawable.ic_music_icon).into(mIvSongOnPlayImg);
            Glide.with(this).load(R.drawable.ic_music_icon).into(mIvSongPlayIcon);
        }
    }

}