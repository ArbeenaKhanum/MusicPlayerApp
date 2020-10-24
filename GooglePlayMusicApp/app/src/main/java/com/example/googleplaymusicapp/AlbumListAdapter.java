package com.example.googleplaymusicapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {
    public List<AlbumSongsModel> albumSongsModelList;
    public ItemClickListener itemClickListener;

    public AlbumListAdapter(List<AlbumSongsModel> albumSongsModelList, ItemClickListener itemClickListener) {
        this.albumSongsModelList = albumSongsModelList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_list, parent, false);
        return new AlbumListViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder holder, int position) {
        AlbumSongsModel albumSongsModel = albumSongsModelList.get(position);
        holder.setData(albumSongsModel);
    }

    @Override
    public int getItemCount() {
        return albumSongsModelList.size();
    }
}
