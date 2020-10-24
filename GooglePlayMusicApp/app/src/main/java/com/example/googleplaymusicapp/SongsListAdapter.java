package com.example.googleplaymusicapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListViewHolder> {
    private List<SongsModel> songsModelList;

    public SongsListAdapter(List<SongsModel> songsModelList) {
        this.songsModelList = songsModelList;
    }

    @NonNull
    @Override
    public SongsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list_view, parent, false);
      return new SongsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsListViewHolder holder, int position) {
        SongsModel songsModel = songsModelList.get(position);
        holder.setData(songsModel);
    }

    @Override
    public int getItemCount() {
        return songsModelList.size();
    }
}
