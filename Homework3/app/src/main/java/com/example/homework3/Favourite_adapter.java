package com.example.homework3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Favourite_adapter extends RecyclerView.Adapter<Favourite_adapter.FavouriteViewHolder> {

    private ArrayList<Cat_image> favourite_cats = Itemdetail.addtofavourite;


    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view=
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.favorite_adapter,parent,false);

        FavouriteViewHolder  favouriteViewHolder = new FavouriteViewHolder(view);
        return favouriteViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        if (favourite_cats.isEmpty() == true) {
            holder.fav_breed_name.setText("Add your cat first!");
        } else {

           // System.out.println(favourite_cats.get(position).getBreeds().get(position).getName());
            holder.fav_breed_name.setText(favourite_cats.get(position).getBreeds().get(0).getName());
        }
    }
    @Override
    public int getItemCount(){ return favourite_cats.size();}

   public static class FavouriteViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView fav_breed_name;

        public FavouriteViewHolder(View v){
            super(v);
            view =v;
            fav_breed_name=v.findViewById(R.id.textView_fav_adapter);

        }
   }
}
