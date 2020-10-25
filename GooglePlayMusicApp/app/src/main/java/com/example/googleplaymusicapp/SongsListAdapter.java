package com.example.googleplaymusicapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongListViewHolder> {
    ArrayList<SongsModel> songsModelArrayList;
    Context mContext;

    public SongsListAdapter(Context mContext, ArrayList<SongsModel> songsModelArrayList) {
        this.mContext = mContext;
        this.songsModelArrayList = songsModelArrayList;
    }

    @NonNull
    @Override
    public SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.songs_list_view, parent, false);
        return new SongListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongListViewHolder holder, int position) {
        holder.mTvSongsTitle.setText(songsModelArrayList.get(position).getTitle());

        byte[] songImg = getSongsImg(songsModelArrayList.get(position).getResId());
        if (songImg != null) {
            Glide.with(holder.mIvSongsImg).load(songImg).into(holder.mIvSongsImg);
        } else {
            Glide.with(holder.mIvSongsImg).load(R.drawable.ic_music_icon).into(holder.mIvSongsImg);
        }
    }

    @Override
    public int getItemCount() {
        return songsModelArrayList.size();
    }


    public class SongListViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvSongsImg;
        TextView mTvSongsTitle;
        RelativeLayout rlSongsList;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        public void initViews(View itemView) {
            mTvSongsTitle = itemView.findViewById(R.id.tvSongTitle);
            mIvSongsImg = itemView.findViewById(R.id.ivSongsImage);
            rlSongsList = itemView.findViewById(R.id.rlSongList);

            rlSongsList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent musicPlayerIntent = new Intent(mContext, MusicPlayerActivity.class);
                    mContext.startActivity(musicPlayerIntent);
                }
            });
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