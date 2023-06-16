package com.kelvin.travelling;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationHome);
        bottomNavigationView.setSelectedItemId(R.id.home_Navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_Navigation) {
                return true;
            } else if (item.getItemId() == R.id.profile_Navigation) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(new Intent(getApplicationContext(), navProfileActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.favorite_Navigation) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(new Intent(getApplicationContext(), navFavoriteActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.places_Navigation) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(new Intent(getApplicationContext(), navPlacesActivity.class));
                finish();
                return true;
            }
            return false;
        });

    }


}



