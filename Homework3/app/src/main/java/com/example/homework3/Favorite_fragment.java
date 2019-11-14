package com.example.homework3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Favorite_fragment extends Fragment {
  private RecyclerView recyclerView;

    public Favorite_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyckeview_favourite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        Favourite_adapter favourite_adapter = new Favourite_adapter();
        recyclerView.setAdapter(favourite_adapter);

        return view;



    }

}
