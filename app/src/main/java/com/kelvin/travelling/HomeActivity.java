package com.kelvin.travelling;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbarHome;
    TextView tvHiUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bottomNavigationView = findViewById(R.id.bottomNavigationHome);
        toolbarHome = findViewById(R.id.topBarApp);
        tvHiUser = findViewById(R.id.tv_hi_user);

        bottomNavigationView.setSelectedItemId(R.id.home_Navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_Navigation) {
                return true;
            } else if (item.getItemId() == R.id.profile_Navigation) {
                startActivity(new Intent(getApplicationContext(), navProfileActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.favorite_Navigation) {
                startActivity(new Intent(getApplicationContext(), navFavoriteActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.places_Navigation) {
                startActivity(new Intent(getApplicationContext(), navPlacesActivity.class));
                finish();
                return true;
            }
            return false;
        });

        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        Log.d("HomeActivity", "Username: " + username);
        Log.d("HomeActivity", "Email: " + email);
        Log.d("HomeActivity", "Password: " + password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String welcomeMsg = getString(R.string.welcome_message, username);
            tvHiUser.setText(welcomeMsg);
        }

        setSupportActionBar(toolbarHome);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemMenu = item.getItemId();
        //item.setChecked(true);

        if (itemMenu == R.id.castle_nav) {

            String urlDisney = "https://www.disneylandparis.com/es-es/";
            Intent goToUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDisney));
            startActivity(goToUrl);

            return true;

        } else if (itemMenu == R.id.car_nav) {

            replaceFragment(new FragmentLilac());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutLilac, fragment);
        fragmentTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_home, menu);

        return true;
    }

    public void btnNavBack(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}



