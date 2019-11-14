package com.example.homework3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationItemView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Search_fragment searchFragment = new Search_fragment();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.replace(R.id.frameLayout,searchFragment);
        fragmentTransaction1.commit();

        //Set up bottomnavigationView
        bottomNavigationItemView = findViewById(R.id.bottomNavigationView);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_search:
                        Search_fragment searchFragment = new Search_fragment();
                        FragmentManager fragmentManager1 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        fragmentTransaction1.replace(R.id.frameLayout,searchFragment);
                        fragmentTransaction1.commit();
                        break;
                    case R.id.navigation_fav:
                        Favorite_fragment favorite_fragment = new Favorite_fragment();
                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.frameLayout,favorite_fragment);
                        fragmentTransaction2.commit();


                        break;

                }
                return true;
            }
        });


    }
}
