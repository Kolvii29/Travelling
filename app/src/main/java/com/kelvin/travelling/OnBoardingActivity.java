package com.kelvin.travelling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding);

        buttons();
    }

    public void buttons(){
        Button btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(v -> {

            Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}