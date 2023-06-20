package com.kelvin.travelling;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navFavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_favorite);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationHome);
        bottomNavigationView.setSelectedItemId(R.id.favorite_Navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_Navigation) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_Navigation) {
                startActivity(new Intent(getApplicationContext(), navProfileActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.favorite_Navigation) {

                return true;
            } else if (item.getItemId() == R.id.places_Navigation) {
                startActivity(new Intent(getApplicationContext(), navPlacesActivity.class));
                finish();
                return true;
            }
            return false;
        });

    }
}