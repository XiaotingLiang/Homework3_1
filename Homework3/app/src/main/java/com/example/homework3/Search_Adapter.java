package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Search_Adapter  extends RecyclerView.Adapter<Search_Adapter.SearchViewHolder> {

     private List<breeds> breedlist;

    public void setData(List<breeds> breedListadpt){
        this.breedlist = breedListadpt;
     }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_adapter, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        return searchViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull  SearchViewHolder holder, final int position){
        final String breed_id = breedlist.get(position).getId();

        holder.textView_searchadapt.setText(breedlist.get(position).getName());
        holder.textView_searchadapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,Itemdetail.class);
                intent.putExtra("breed_name",breedlist.get(position).getName());
                intent.putExtra("breed_id",breedlist.get(position).getId());
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return breedlist.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView textView_searchadapt;

        public SearchViewHolder(View v){
            super(v);
            view = v;
            textView_searchadapt =v.findViewById(R.id.textView_searchadapter);

        }
    }


}