package com.example.googleplaymusicapp.adapters;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.googleplaymusicapp.MainActivity;
import com.example.googleplaymusicapp.MusicPlayerActivity;
import com.example.googleplaymusicapp.R;
import com.example.googleplaymusicapp.SongsModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongListViewHolder> {
    public static ArrayList<SongsModel> songsModelArrayList;
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
    public void onBindViewHolder(@NonNull SongListViewHolder holder, final int position) {
        holder.mTvSongsTitle.setText(songsModelArrayList.get(position).getTitle());

        byte[] songImg = getSongsImg(songsModelArrayList.get(position).getResId());
        if (songImg != null) {
            Glide.with(holder.mIvSongsImg).load(songImg).into(holder.mIvSongsImg);
        } else {
            Glide.with(holder.mIvSongsImg).load(R.drawable.ic_music_icon).into(holder.mIvSongsImg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicPlayerIntent = new Intent(mContext, MusicPlayerActivity.class);
                musicPlayerIntent.putExtra("position", position);
                mContext.startActivity(musicPlayerIntent);
            }
        });

        holder.mIvMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, v);
                popupMenu.getMenuInflater().inflate(R.menu.more_options, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tvDelete:
                                Toast.makeText(mContext, "Delete song", Toast.LENGTH_SHORT).show();
                                deleteFile(position, v);
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    private void deleteFile(int position, View v) {
        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.
                EXTERNAL_CONTENT_URI, Long.parseLong(songsModelArrayList.get(position).getId()));

        File file = new File(songsModelArrayList.get(position).getResId());
        boolean isDeleted = file.delete();
        if (isDeleted) {
            mContext.getContentResolver().delete(contentUri, null, null);
            songsModelArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, songsModelArrayList.size());
            Snackbar.make(v, "Song Deleted", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(v, "Can't be Deleted", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return songsModelArrayList.size();
    }


    public class SongListViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvSongsImg;
        ImageView mIvMoreOptions;
        TextView mTvSongsTitle;
        RelativeLayout rlSongsList;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        public void initViews(View itemView) {
            mTvSongsTitle = itemView.findViewById(R.id.tvSongTitle);
            mIvMoreOptions = itemView.findViewById(R.id.ivMoreOptions);
            mIvSongsImg = itemView.findViewById(R.id.ivSongsImage);
            rlSongsList = itemView.findViewById(R.id.rlSongList);
        }
    }

    private byte[] getSongsImg(String imageFile) {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(imageFile);
        byte[] img = metadataRetriever.getEmbeddedPicture();
        metadataRetriever.release();
        return img;
    }

    public void updateListAdapter(ArrayList<SongsModel> modelList) {
        songsModelArrayList = new ArrayList<>();
        songsModelArrayList.addAll(modelList);
        notifyDataSetChanged();
    }
}