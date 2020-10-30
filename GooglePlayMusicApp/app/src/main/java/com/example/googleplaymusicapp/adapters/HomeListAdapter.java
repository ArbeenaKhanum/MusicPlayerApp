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
import com.example.googleplaymusicapp.viewholders.HomeListViewHolder;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListViewHolder> {
    private List<SongsModel> homeSongsList;
    private Context mContext;

    public HomeListAdapter(List<SongsModel> homeSongsList, Context mContext) {
        this.homeSongsList = homeSongsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items_list, parent, false);
        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, final int position) {
        SongsModel songsModel = homeSongsList.get(position);
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
        return homeSongsList.size();
    }
}
