package com.example.googleplaymusicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.googleplaymusicapp.adapters.FragmentAdapter;
import com.example.googleplaymusicapp.adapters.SongsListAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final int REQUEST_CODE = 101;
    static ArrayList<SongsModel> songsModels;
    private SongsListAdapter songsListAdapter;
    public static boolean isShuffled = false;
    public static boolean isRepeat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setFragmentAdapter();
//        Intent intentUp = new Intent(MainActivity.this, MusicPlayerActivity.class);
//        startActivity(intentUp);
//        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
//
//        Intent intentDown = new Intent(MainActivity.this, MusicPlayerActivity.class);
//        startActivity(intentDown);
//        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
        permission();
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            songsModels = getSongs(this);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                songsModels = getSongs(this);

            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission already denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setFragmentAdapter() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),
                FragmentAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
    }

    public static ArrayList<SongsModel> getSongs(Context context) {
        ArrayList<SongsModel> songsModelArrayList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media._ID
        };

        Cursor cursor = context.getContentResolver().
                query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String data = cursor.getString(3);
                String artists = cursor.getString(4);
                String id = cursor.getString(5);

                SongsModel songsModel = new SongsModel(data, title, album, artists, duration, id);
                Log.d("SongList", "data" + data + "title" + title + "album" + album +
                        "artists" + artists + "duration" + duration);
                songsModelArrayList.add(songsModel);
            }
            cursor.close();
        }
        return songsModelArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.searchOption);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        ArrayList<SongsModel> modelArrayList = new ArrayList<>();
        for (SongsModel songs : modelArrayList) {
            if (songs.getTitle().toLowerCase().contains(userInput)) {
                modelArrayList.add(songs);
            }
        }
        SongListFragment.songsListAdapter.updateListAdapter(modelArrayList);
        return true;
    }
}