package com.example.dairyfarming.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dairyfarming.R;

public class AddBuffaloDialog extends Dialog {

    public interface OnBuffaloAddedListener {
        void onBuffaloAdded(String name, double liters);
    }

    private OnBuffaloAddedListener listener;

    private EditText nameInput, litersInput;
    private Button addButton;

    public AddBuffaloDialog(Context context) {
        super(context);
    }

    public void setOnBuffaloAddedListener(OnBuffaloAddedListener listener) {
        this.listener = listener;
    }

    // For editing: pre-fill values AFTER onCreate() when views are initialized
    public void setBuffaloData(String name, double liters) {
        // If views are already initialized, set values immediately
        if (nameInput != null && litersInput != null) {
            nameInput.setText(name);
            litersInput.setText(String.valueOf(liters));
        } else {
            // If not initialized yet, delay setting using a listener on onCreate
            // We'll save the values and set them once views are ready
            this.pendingName = name;
            this.pendingLiters = liters;
        }
    }

    // To handle delayed setting of initial values before views are ready
    private String pendingName = null;
    private double pendingLiters = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_buffalo);

        nameInput = findViewById(R.id.buffaloNameInput);
        litersInput = findViewById(R.id.buffaloLitersInput);
        addButton = findViewById(R.id.addBuffaloButton);

        // If we have pending data to set, do it now
        if (pendingName != null) {
            nameInput.setText(pendingName);
            litersInput.setText(String.valueOf(pendingLiters));
            // Clear pending data
            pendingName = null;
        }

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String litersStr = litersInput.getText().toString().trim();

            if (name.isEmpty() || litersStr.isEmpty()) {
                Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double liters = Double.parseDouble(litersStr);
                if (listener != null) {
                    listener.onBuffaloAdded(name, liters);
                }
                dismiss();
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid liters", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
