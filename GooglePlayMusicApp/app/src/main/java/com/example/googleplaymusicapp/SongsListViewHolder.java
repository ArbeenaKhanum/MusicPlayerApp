package com.example.googleplaymusicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class SongsListViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvSongsImg;
    private TextView mTvSongsTitle;
    private TextView mTvSongsArtist;

    public SongsListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvSongsImg = itemView.findViewById(R.id.ivSongsImage);
        mTvSongsTitle = itemView.findViewById(R.id.tvSongTitle);
        mTvSongsArtist = itemView.findViewById(R.id.tvArtistName);
    }

    public void setData(SongsModel songsModel) {
        if (mIvSongsImg != null) {
            Glide.with(mIvSongsImg).load(songsModel.getResId()).placeholder(R.drawable.ic_music_icon).into(mIvSongsImg);
        } else {
            Glide.with(mIvSongsImg).load(R.drawable.ic_music_icon).into(mIvSongsImg);
        }

        mTvSongsTitle.setText(songsModel.getSongName());
        mTvSongsArtist.setText(songsModel.getArtistsName());
    }
}
