package com.example.googleplaymusicapp.viewholders;

import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.googleplaymusicapp.R;
import com.example.googleplaymusicapp.SongsModel;

public class AlbumListViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvAlbumImg;
    private TextView mTvAlbumTitle;
    private CardView mCvAlbumCard;

    public AlbumListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvAlbumImg = itemView.findViewById(R.id.ivAlbumImage);
        mTvAlbumTitle = itemView.findViewById(R.id.tvAlbumTitle);
        mCvAlbumCard = itemView.findViewById(R.id.cvAlbumCard);
    }

    public void setData(SongsModel songsModel) {
        mTvAlbumTitle.setText(songsModel.getAlbumName());

        byte[] songImg = getSongsImg(songsModel.getResId());
        if (songImg != null) {
            Glide.with(mIvAlbumImg).load(songImg).into(mIvAlbumImg);
        } else {
            Glide.with(mIvAlbumImg).load(R.drawable.ic_music_icon).into(mIvAlbumImg);
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
