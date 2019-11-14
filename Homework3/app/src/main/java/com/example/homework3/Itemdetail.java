package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Itemdetail extends AppCompatActivity {
    private TextView name;
    private TextView weight;
    private TextView origin;
    private TextView lifespan;
    private TextView dogfriend;
    private TextView description;
    private TextView temperament;
    private TextView wikilink;
    private ImageView image;
    private Button addtofav;

    public static ArrayList<Cat_image> addtofavourite = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        // Get the string from searchview and pass breed_name
        Intent intent = getIntent();
        final String breedname = intent.getStringExtra("breed_name");
        final String breedid = intent.getStringExtra("breed_id");
        final breeds breed = new breeds();
        breed.setName(breedname);
        // set stuff of layout
        name = findViewById(R.id.detail_name);
        weight =findViewById(R.id.detail_weight);
        origin = findViewById(R.id.detail_origin);
        lifespan = findViewById(R.id.detail_lifespan);
        dogfriend = findViewById(R.id.detail_dogfriendless);
        description = findViewById(R.id.detail_description);
        temperament = findViewById(R.id.detail_temperament);
        wikilink = findViewById(R.id.detail_wiki);
        image = findViewById(R.id.detail_imageView);
        addtofav = findViewById(R.id.addtofav);

        //
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.thecatapi.com/v1/images/search?breed_id="+breedid;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                try {
                    Gson gson = new Gson();
                    Cat_image[] images = gson.fromJson(response, Cat_image[].class);
                    final List<Cat_image> imageList = Arrays.asList(images);
                    String imageurl = imageList.get(0).getUrl();
                        Glide.with(getApplicationContext()).load(imageurl).into(image);

                    name.setText("name: " + imageList.get(0).getBreeds().get(0).getName());
                    weight.setText("weight: " + imageList.get(0).getBreeds().get(0).getWeights().getMetric());
                    origin.setText("origin: " + imageList.get(0).getBreeds().get(0).getOrigin());
                    lifespan.setText("lifespan: " + imageList.get(0).getBreeds().get(0).getLife_span());
                    dogfriend.setText("dogfriendless: " + String.valueOf(imageList.get(0).getBreeds().get(0).getDog_friendly()));
                    description.setText("description: " + imageList.get(0).getBreeds().get(0).getDescription());
                    temperament.setText("temperament: " + imageList.get(0).getBreeds().get(0).getTemperament());
                    wikilink.setText("wikipedia_url: " + imageList.get(0).getBreeds().get(0).getWikipedia_url());

                    //add to favourite cat
                    addtofav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addtofavourite.add(imageList.get(0));
                            Context context = v.getContext();
                            Intent intent = new Intent(context,MainActivity.class);
                            context.startActivity(intent);

                        }
                    });

                    requestQueue.stop();

                } catch (Exception e){
                    image.setImageResource(R.drawable.nopics);
                    name.setText("name: "+breedname);
                    weight.setText("weight: No detail for this cat");
                    origin.setText("origin: No detail for this cat" );
                    lifespan.setText("lifespan: No detail for this cat" );
                    dogfriend.setText("dogfriendless: No detail for this cat" );
                    description.setText("description: No detail for this cat" );
                    temperament.setText("temperament: No detail for this cat");
                    wikilink.setText("wikipedia_url: No detail for this cat");



                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);
















    }


}
