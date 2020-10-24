package com.example.googleplaymusicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class AlbumListViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private ImageView mIvAlbumImg;
    private TextView mTvAlbumTitle;

    public AlbumListViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvAlbumImg = itemView.findViewById(R.id.ivAlbumImage);
        mTvAlbumTitle = itemView.findViewById(R.id.tvAlbumTitle);
    }

    public void setData(AlbumSongsModel albumSongsModel) {
        Glide.with(mIvAlbumImg).load(albumSongsModel.getResIdImg()).
                placeholder(R.drawable.ic_music_icon).into(mIvAlbumImg);
        mTvAlbumTitle.setText(albumSongsModel.getSongTitle());
    }
}
