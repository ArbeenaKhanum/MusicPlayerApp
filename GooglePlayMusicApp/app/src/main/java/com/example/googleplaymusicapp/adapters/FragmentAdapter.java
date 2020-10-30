package com.example.googleplaymusicapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.googleplaymusicapp.AlbumFragment;
import com.example.googleplaymusicapp.ArtistFragment;
import com.example.googleplaymusicapp.HomeFragment;
import com.example.googleplaymusicapp.SongListFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance("Home Fragment");
            case 1:
                return SongListFragment.newInstance("Songs List Fragment");
            case 2:
                return AlbumFragment.newInstance("Albums Fragment");
            case 3:
                return ArtistFragment.newInstance("Artists Fragment");
        }
        return SongListFragment.newInstance("Default");
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Songs";
            case 2:
                return "Albums";
            case 3:
                return "Artists";
        }
        return "Default";
    }
}
