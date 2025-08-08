package com.example.dairyfarming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBuffaloActivity extends AppCompatActivity {

    EditText editName, editMilkProduction;
    Button btnSave;

    String buffaloId; // Unique key from DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buffalo);

        editName = findViewById(R.id.editName);
        editMilkProduction = findViewById(R.id.editMilkProduction);
        btnSave = findViewById(R.id.btnSave);

        // Get buffalo details from Intent
        Intent intent = getIntent();
        buffaloId = intent.getStringExtra("buffaloId");
        editName.setText(intent.getStringExtra("name"));
        editMilkProduction.setText(intent.getStringExtra("milkProduction"));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String milkProduction = editMilkProduction.getText().toString();

                // TODO: Update in DB (Firebase / SQLite)
                Toast.makeText(EditBuffaloActivity.this, "Buffalo updated!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
