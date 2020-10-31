package com.example.googleplaymusicapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplaymusicapp.adapters.SongsListAdapter;

import java.util.ArrayList;

import static com.example.googleplaymusicapp.MainActivity.songsModels;


public class SongListFragment extends Fragment {

    private RecyclerView mRvListOfSongs;
    public static SongsListAdapter songsListAdapter;
    private ArrayList<SongsModel> songsModelArrayList;
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public SongListFragment() {
        // Required empty public constructor
    }

    public static SongListFragment newInstance(String param1) {
        SongListFragment fragment = new SongListFragment();
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
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);
        mRvListOfSongs = view.findViewById(R.id.rvListOfSongs);
        mRvListOfSongs.setHasFixedSize(true);

        if (!(songsModels.size() < 1)) {
            songsListAdapter = new SongsListAdapter(getContext(), songsModels);
            mRvListOfSongs.setAdapter(songsListAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL, false);
            mRvListOfSongs.setLayoutManager(linearLayoutManager);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}