package com.kelvin.travelling;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navPlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_places);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationHome);
        bottomNavigationView.setSelectedItemId(R.id.places_Navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_Navigation) {
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_Navigation) {
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                startActivity(new Intent(getApplicationContext(), navProfileActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.favorite_Navigation) {
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                startActivity(new Intent(getApplicationContext(), navFavoriteActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.places_Navigation) {

                return true;
            }
            return false;
        });

    }
}