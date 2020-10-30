package com.example.googleplaymusicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplaymusicapp.adapters.ArtistsListAdapter;

import java.util.ArrayList;

import static com.example.googleplaymusicapp.MainActivity.songsModels;

public class ArtistFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private RecyclerView mRvListOfArtists;
    private ArtistsListAdapter artistsListAdapter;
    private ArrayList<SongsModel> songsModelArrayList;

    public ArtistFragment() {
        // Required empty public constructor
    }

    public static ArtistFragment newInstance(String param1) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvListOfArtists = view.findViewById(R.id.rvListOfArtists);
        mRvListOfArtists.setHasFixedSize(true);

        if (!(songsModels.size() < 1)) {
            artistsListAdapter = new ArtistsListAdapter(getContext(), songsModels);
            mRvListOfArtists.setAdapter(artistsListAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
            mRvListOfArtists.setLayoutManager(gridLayoutManager);
        }
    }
}