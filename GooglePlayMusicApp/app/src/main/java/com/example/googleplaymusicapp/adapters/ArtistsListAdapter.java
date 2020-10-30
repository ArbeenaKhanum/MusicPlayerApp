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
import com.example.googleplaymusicapp.viewholders.ArtistsViewHolder;

import java.util.ArrayList;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsViewHolder> {
    public ArrayList<SongsModel> artistsArrayList;
    public Context mContext;

    public ArtistsListAdapter(Context mContext, ArrayList<SongsModel> artistsArrayList) {
        this.artistsArrayList = artistsArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ArtistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artists_items_list, parent, false);
        return new ArtistsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsViewHolder holder, final int position) {
        SongsModel songsModel = artistsArrayList.get(position);
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
        return artistsArrayList.size();
    }
}
