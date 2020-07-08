package com.example.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlaylistFragment extends Fragment {

    public PlaylistFragment() {
        // Required empty public constructor
    }


    public static PlaylistFragment newInstance() {
//        PlaylistFragment fragment = new PlaylistFragment();
//        Bundle args = new Bundle();
//        args.putInt("page", page);
//        args.putString("name", name);
//        fragment.setArguments(args);
        return new PlaylistFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }
}
