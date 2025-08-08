package com.example.dairyfarming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.dairyfarming.dialogs.AddBuffaloDialog;
import com.example.dairyfarming.model.AppDatabase;
import com.example.dairyfarming.model.MilkRecord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addBuffaloButton;
    private ImageView profileIcon;
    private BuffaloAdapter buffaloAdapter;
    private List<MilkRecord> buffaloList = new ArrayList<>();
    private AppDatabase db;
    private final Executor dbExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        addBuffaloButton = findViewById(R.id.addBuffaloButton);
        profileIcon = findViewById(R.id.profileIcon);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "dairy-farming-db")
                .fallbackToDestructiveMigration()
                .build();

        buffaloAdapter = new BuffaloAdapter(this, new ArrayList<>(), new BuffaloAdapter.OnBuffaloClickListener() {
            @Override
            public void onClick(BuffaloModel buffalo) {
                // Edit on click
                AddBuffaloDialog dialog = new AddBuffaloDialog(DashboardActivity.this);
                dialog.setBuffaloData(buffalo.getName(), Double.parseDouble(buffalo.getMilkProduction()));
                dialog.setOnBuffaloAddedListener((name, liters) -> {
                    dbExecutor.execute(() -> {
                        MilkRecord record = db.milkRecordDao().getRecordById(buffalo.getId());
                        if (record != null) {
                            record.setName(name);
                            record.setLiters(liters);
                            db.milkRecordDao().update(record);
                            runOnUiThread(DashboardActivity.this::loadBuffaloRecords);
                        }
                    });
                });
                dialog.show();
            }

            @Override
            public void onLongClick(BuffaloModel buffalo) {
                new AlertDialog.Builder(DashboardActivity.this)
                        .setTitle("Delete Buffalo")
                        .setMessage("Are you sure you want to delete " + buffalo.getName() + "?")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            dbExecutor.execute(() -> {
                                MilkRecord record = db.milkRecordDao().getRecordById(buffalo.getId());
                                if (record != null) {
                                    db.milkRecordDao().delete(record);
                                    runOnUiThread(DashboardActivity.this::loadBuffaloRecords);
                                }
                            });
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(buffaloAdapter);

        loadBuffaloRecords();

        addBuffaloButton.setOnClickListener(v -> {
            AddBuffaloDialog dialog = new AddBuffaloDialog(DashboardActivity.this);
            dialog.setOnBuffaloAddedListener((name, liters) -> {
                dbExecutor.execute(() -> {
                    MilkRecord newRecord = new MilkRecord(name, liters, System.currentTimeMillis(), "Buffalo");
                    db.milkRecordDao().insert(newRecord);
                    runOnUiThread(this::loadBuffaloRecords);
                });
            });
            dialog.show();
        });

        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void loadBuffaloRecords() {
        dbExecutor.execute(() -> {
            List<MilkRecord> records = db.milkRecordDao().getAllRecords();
            runOnUiThread(() -> {
                buffaloList.clear();
                buffaloList.addAll(records);

                List<BuffaloModel> buffaloModels = new ArrayList<>();
                for (MilkRecord record : buffaloList) {
                    buffaloModels.add(new BuffaloModel(
                            record.getId(),
                            record.getName(),
                            String.valueOf(record.getLiters()),
                            "Buffalo"
                    ));
                }

                buffaloAdapter.updateList(buffaloModels);
            });
        });
    }
}
