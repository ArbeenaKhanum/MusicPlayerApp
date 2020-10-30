package com.example.googleplaymusicapp;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AppNotification extends Application {

//    public static final String CHANNEL_ID_1 = "song1";
//    public static final String CHANNEL_ID_2 = "song2";
//    public static final String ACTION_PREVIOUS = "previous";
//    public static final String ACTION_NEXT = "next";
//    public static final String ACTION_PLAY = "play";
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationBar();
//        } else {
//        }
//        createNotificationBar();
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private void createNotificationBar() {
//
//        NotificationChannel channelOne = new NotificationChannel(CHANNEL_ID_1, "Channel(1)",
//                NotificationManager.IMPORTANCE_HIGH);
//        channelOne.setDescription("Channel 1 Desc");
//
//        NotificationChannel channelTwo = new NotificationChannel(CHANNEL_ID_2, "Channel(2)",
//                NotificationManager.IMPORTANCE_HIGH);
//        channelTwo.setDescription("Channel 2 Desc");
//
//        NotificationManager notificationManager = getSystemService(NotificationManager.class);
//        notificationManager.createNotificationChannel(channelOne);
//        notificationManager.createNotificationChannel(channelTwo);
//
//        NotificationCompat.Builder notificationBuilderOne = new NotificationCompat.Builder(this, CHANNEL_ID_1);
//        Notification notificationOne = notificationBuilderOne.setOngoing(true)
//                .setContentTitle("App is running in background")
//                .setPriority(NotificationManager.IMPORTANCE_MIN)
//                .setCategory(Notification.CATEGORY_SERVICE)
//                .build();
//        notificationBuilderOne.addAction(R.drawable.ic_songs_thumb_down, "Dislike", null );
//        notificationBuilderOne.addAction(R.drawable.ic_next_songs_icon, "Next song", null );
//        notificationBuilderOne.addAction(R.drawable.ic_previous_song_icon, "Previous song", null );
//        notificationBuilderOne.addAction(R.drawable.ic_play_songs_icon, "Play", null );
//        notificationBuilderOne.addAction(R.drawable.ic_songs_thumb_up, "Like", null );
//        notificationBuilderOne.setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
//                .setShowActionsInCompactView(1, 2, 3));
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
//
//        NotificationCompat.Builder notificationBuilderTwo = new NotificationCompat.Builder(this, CHANNEL_ID_2);
//        Notification notificationTwo = notificationBuilderTwo.setOngoing(true)
//                .setContentTitle("App is running in background")
//                .setPriority(NotificationManager.IMPORTANCE_MIN)
//                .setCategory(Notification.CATEGORY_SERVICE)
//                .build();

 //   }
}
