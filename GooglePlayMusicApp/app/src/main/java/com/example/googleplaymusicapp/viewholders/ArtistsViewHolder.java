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

public class ArtistsViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvArtistsImg;
    private TextView mTvArtistsTitle;
    private CardView mCvAlbumCard;

    public ArtistsViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvArtistsImg = itemView.findViewById(R.id.ivArtistsImage);
        mTvArtistsTitle = itemView.findViewById(R.id.tvArtistsTitle);
        mCvAlbumCard = itemView.findViewById(R.id.cvAlbumCard);
    }

    public void setData(SongsModel songsModel) {
        mTvArtistsTitle.setText(songsModel.getArtistsName());

        byte[] songImg = getSongsImg(songsModel.getResId());
        if (songImg != null) {
            Glide.with(mIvArtistsImg).load(songImg).into(mIvArtistsImg);
        } else {
            Glide.with(mIvArtistsImg).load(R.drawable.ic_music_icon).into(mIvArtistsImg);
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
