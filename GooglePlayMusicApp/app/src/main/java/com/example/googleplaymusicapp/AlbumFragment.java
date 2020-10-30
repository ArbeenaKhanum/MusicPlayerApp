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

import com.example.googleplaymusicapp.adapters.AlbumListAdapter;

import java.util.ArrayList;

import static com.example.googleplaymusicapp.MainActivity.songsModels;

public class AlbumFragment extends Fragment {
    private static final String ARG_PARAM2 = "param2";
    private String mParam2;
    private RecyclerView mRvListOfAlbums;
    private AlbumListAdapter albumListAdapter;
    private ArrayList<SongsModel> albumSongsModelArrayList;

    public AlbumFragment() {
        // Required empty public constructor
    }

    public static AlbumFragment newInstance(String param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvListOfAlbums = view.findViewById(R.id.rvListOfAlbums);
        mRvListOfAlbums.setHasFixedSize(true);

        if (!(songsModels.size() < 1)) {
            albumListAdapter = new AlbumListAdapter(getContext(), songsModels);
            mRvListOfAlbums.setAdapter(albumListAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
            mRvListOfAlbums.setLayoutManager(gridLayoutManager);
        }
    }
}