package com.example.dairyfarming;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BuffaloDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffalo_details);

        String buffaloName = getIntent().getStringExtra("buffaloName");
        String milkProduction = getIntent().getStringExtra("milkProduction");

        TextView detailsText = findViewById(R.id.detailsText);
        detailsText.setText("Name: " + buffaloName + "\nMilk Production: " + milkProduction);
    }
}
