package com.example.googleplaymusicapp.viewholders;

import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.googleplaymusicapp.R;
import com.example.googleplaymusicapp.SongsModel;

public class HomeListViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvHomeSongsImg;
    private TextView mTvHomeSongsTitle;
    private LinearLayout mLLHomeList;

    public HomeListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void initViews(View itemView) {
        mTvHomeSongsTitle = itemView.findViewById(R.id.tvHomeSongsTitle);
        mIvHomeSongsImg = itemView.findViewById(R.id.ivHomeSongsImage);
        mLLHomeList = itemView.findViewById(R.id.llHomeList);
    }

    public void setData(SongsModel songsModel) {
       mTvHomeSongsTitle.setText(songsModel.getTitle());

        byte[] songImg = getSongsImg(songsModel.getResId());
        if (songImg != null) {
            Glide.with(mIvHomeSongsImg).load(songImg).into(mIvHomeSongsImg);
        } else {
            Glide.with(mIvHomeSongsImg).load(R.drawable.ic_music_icon).into(mIvHomeSongsImg);
        }
    }

    private byte[] getSongsImg(String imageFile) {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(imageFile);
        byte[] img = metadataRetriever.getEmbeddedPicture();
        metadataRetriever.release();
        return img;
    }
}
