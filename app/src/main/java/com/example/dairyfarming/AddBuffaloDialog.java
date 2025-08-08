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

    public void setOnBuffaloAddedListener(OnBuffaloAddedListener listener) {
        this.listener = listener;
    }

    public AddBuffaloDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_buffalo);

        EditText nameInput = findViewById(R.id.buffaloNameInput);
        EditText litersInput = findViewById(R.id.buffaloLitersInput);
        Button addButton = findViewById(R.id.addBuffaloButton);

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
