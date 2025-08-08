package com.example.dairyfarming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addBuffaloButton;
    private ImageView profileIcon;
    private BuffaloAdapter buffaloAdapter;
    private List<BuffaloModel> buffaloList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        addBuffaloButton = findViewById(R.id.addBuffaloButton);
        profileIcon = findViewById(R.id.profileIcon);

        buffaloList = new ArrayList<>();

        buffaloAdapter = new BuffaloAdapter(
                DashboardActivity.this,
                buffaloList,
                new BuffaloAdapter.OnBuffaloClickListener() {
                    @Override
                    public void onBuffaloClick(BuffaloModel buffalo) {
                        // Example: Open buffalo details when clicked
                        Intent intent = new Intent(DashboardActivity.this, BuffaloDetailsActivity.class);
                        intent.putExtra("buffaloName", buffalo.getName());
                        intent.putExtra("milkProduction", buffalo.getMilkProduction());
                        startActivity(intent);
                    }
                }
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(buffaloAdapter);

        addBuffaloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AddBuffaloActivity.class));
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            }
        });
    }
}
