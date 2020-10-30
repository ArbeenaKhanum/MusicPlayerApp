package com.example.googleplaymusicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleplaymusicapp.MusicPlayerActivity;
import com.example.googleplaymusicapp.R;
import com.example.googleplaymusicapp.SongsModel;
import com.example.googleplaymusicapp.viewholders.AlbumListViewHolder;

import java.util.ArrayList;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {
    public ArrayList<SongsModel> albumSongsModelList;
    public Context mContext;

    public AlbumListAdapter(Context mContext, ArrayList<SongsModel> albumSongsModelList) {
        this.albumSongsModelList = albumSongsModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_list, parent, false);
        return new AlbumListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder holder, final int position) {
        SongsModel songsModel = albumSongsModelList.get(position);
        holder.setData(songsModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicPlayerIntent = new Intent(mContext, MusicPlayerActivity.class);
                musicPlayerIntent.putExtra("position", position);
                mContext.startActivity(musicPlayerIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumSongsModelList.size();
    }
}
