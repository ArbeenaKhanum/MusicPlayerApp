package com.example.googleplaymusicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import static com.example.googleplaymusicapp.MainActivity.isRepeat;
import static com.example.googleplaymusicapp.MainActivity.isShuffled;
import static com.example.googleplaymusicapp.MainActivity.songsModels;
import static com.example.googleplaymusicapp.adapters.SongsListAdapter.songsModelArrayList;

public class MusicPlayerActivity extends AppCompatActivity implements
        MediaPlayListener, ServiceConnection {

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
    //static MediaPlayer mediaPlayer;
    private Handler mHandler = new Handler();
    private Thread playThread;
    private Thread pauseThread;
    private Thread previousThread;
    private Thread nextThread;
    MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        initViews();
        getIntentOnClick();

        mBtnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffled) {

                    isShuffled = false;
                    mBtnShuffle.setImageResource(R.drawable.ic_shuffle_off);

                } else {

                    isShuffled = true;
                    mBtnShuffle.setImageResource(R.drawable.ic_songs_shuffle_icon);
                }
            }
        });

        mBtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepeat) {

                    isRepeat = false;
                    mBtnRepeat.setImageResource(R.drawable.ic_repeat_off);

                } else {

                    isRepeat = true;
                    mBtnRepeat.setImageResource(R.drawable.ic_songs_repeat_icon);
                }
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (musicService != null && fromUser) {
                    musicService.seekTo(progress * 1000);
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
                if (musicService != null) {
                    int currentPosition = musicService.getCurrentPosition() / 1000;
                    mSeekBar.setProgress(currentPosition);
                    mTvDurationStart.setText(durationFormat(currentPosition));
                }

                mHandler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        Intent intentMusicService = new Intent(this, MusicService.class);
        bindService(intentMusicService, this, BIND_AUTO_CREATE);
        playThreadButton();
        previousThreadButton();
        nextThreadButton();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
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

    public void nextBtnClicked() {
        if (musicService.isPlaying()) {
            musicService.stop();
            musicService.release();

            if(isShuffled && !isRepeat) {
                position = getRandom(modelSongsList.size() - 1);
            } else if(!isShuffled && !isRepeat) {
                position = ((position + 1) % modelSongsList.size());
          }
//            else {
//               position is same position;
//            }

            uri = Uri.parse(modelSongsList.get(position).getResId());
            musicService.createMediaPlayer(position);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(musicService.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            musicService.start();
        } else {
            musicService.stop();
            musicService.release();

            if(isShuffled && !isRepeat) {
                position = getRandom(modelSongsList.size() - 1);
            } else if(!isShuffled && !isRepeat) {
                position = ((position + 1) % modelSongsList.size());
            }

            uri = Uri.parse(modelSongsList.get(position).getResId());
            musicService.createMediaPlayer(position);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(musicService.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_play_songs_icon);
        }
    }

    private int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i + 1);
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

    public void previousBtnClicked() {
        if (musicService.isPlaying()) {
            musicService.stop();
            musicService.release();

            if(isShuffled && !isRepeat) {
                position = getRandom(modelSongsList.size() - 1);
            } else if(!isShuffled && !isRepeat) {
                position = ((position - 1) <= 0 ? (modelSongsList.size() - 1) : (position - 1));
            }

            uri = Uri.parse(modelSongsList.get(position).getResId());
            musicService.createMediaPlayer(position);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(musicService.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });

            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            musicService.start();

        } else {
            musicService.stop();
            musicService.release();

            if(isShuffled && !isRepeat) {
                position = getRandom(modelSongsList.size() - 1);
            } else if(!isShuffled && !isRepeat) {
                position = ((position - 1) <= 0 ? (modelSongsList.size() - 1) : (position - 1));
            }

            uri = Uri.parse(modelSongsList.get(position).getResId());
            musicService.createMediaPlayer(position);
            metaData(uri);
            mTvSongOnPlayName.setText(modelSongsList.get(position).getTitle());
            mTvOnPlayArtistName.setText(modelSongsList.get(position).getArtistsName());

            mSeekBar.setMax(musicService.getDuration() / 1000);
            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
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

    public void playPauseBtnClicked() {
        if (musicService.isPlaying()) {
            mBtnPlayPause.setImageResource(R.drawable.ic_play_songs_icon);
            musicService.pause();
            mSeekBar.setMax(musicService.getDuration() / 1000);

            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
                        mSeekBar.setProgress(currentPosition);
                    }

                    mHandler.postDelayed(this, 1000);
                }
            });
        } else {
            mBtnPlayPause.setImageResource(R.drawable.ic_songs_pause_icon);
            musicService.start();
            mSeekBar.setMax(musicService.getDuration() / 1000);

            MusicPlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (musicService != null) {
                        int currentPosition = musicService.getCurrentPosition() / 1000;
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

        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("servicePosition", position);
        startService(intent);
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

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicService.MusicServiceBinder musicServiceBinder = (MusicService.MusicServiceBinder) service;
        musicService = musicServiceBinder.getMusicService();
        mSeekBar.setMax(musicService.getDuration() / 1000);
        metaData(uri);
        mTvSongOnPlayName.setText(songsModels.get(position).getTitle());
        mTvOnPlayArtistName.setText(songsModels.get(position).getArtistsName());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}