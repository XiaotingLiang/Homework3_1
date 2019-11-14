package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class Search_fragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchView searchView;



    public Search_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_fragment,container,false);

        searchView = view.findViewById(R.id.searchView_search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CharSequence breedname = searchView.getQuery();

                // String breedname = getActivity().getIntent().getStringExtra("breed_name");
                final Search_Adapter search_adapter = new Search_Adapter();
                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                String url = "https://api.thecatapi.com/v1/breeds/search?q="+breedname;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Gson gson = new Gson();
                        breeds[] breed = gson.fromJson(response,breeds[].class);
                        List<breeds> breedList = Arrays.asList(breed);
                        search_adapter.setData(breedList);
                        recyclerView.setAdapter(search_adapter);
                        requestQueue.stop();

                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                        errorListener);
                requestQueue.add(stringRequest);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });







        recyclerView = view.findViewById(R.id.recyclerView_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);





        return view;
    }


}
