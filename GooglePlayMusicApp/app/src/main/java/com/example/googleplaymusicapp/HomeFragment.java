package com.example.googleplaymusicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplaymusicapp.adapters.HomeListAdapter;

import java.util.List;

import static com.example.googleplaymusicapp.MainActivity.songsModels;


public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private RecyclerView mRvListOfHomeOne;
    private RecyclerView mRvListOfHomeTwo;
    private HomeListAdapter homeListAdapter;
    private List<SongsModel> homeSongsList;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvListOfHomeOne = view.findViewById(R.id.rvHomeListOne);
        mRvListOfHomeTwo = view.findViewById(R.id.rvHomeListTwo);

        if (!(songsModels.size() < 1)) {
            homeListAdapter = new HomeListAdapter(songsModels, getContext());
            mRvListOfHomeOne.setAdapter(homeListAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mRvListOfHomeOne.setHasFixedSize(true);
           mRvListOfHomeOne.setLayoutManager(linearLayoutManager);
        }

        if (!(songsModels.size() < 1)) {
            homeListAdapter = new HomeListAdapter(songsModels, getContext());
            mRvListOfHomeTwo.setAdapter(homeListAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mRvListOfHomeTwo.setHasFixedSize(true);
            linearLayoutManager.setReverseLayout(true);
            mRvListOfHomeTwo.setLayoutManager(linearLayoutManager);
        }
    }
}