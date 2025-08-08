package com.example.dairyfarming;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dairyfarming.R;

public class SignupActivity extends AppCompatActivity {

    EditText signupEmail, signupPassword;
    Button signupButton, loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.signupButton);
        loginRedirect = findViewById(R.id.loginRedirect);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();

                // Email validation
                if (email.isEmpty()) {
                    signupEmail.setError("Email is required");
                    signupEmail.requestFocus();
                    return;
                }

                // Strict Gmail-only check
                String gmailPattern = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
                if (!email.matches(gmailPattern)) {
                    signupEmail.setError("Enter a valid Gmail address");
                    signupEmail.requestFocus();
                    return;
                }

                // Password validation
                if (password.isEmpty()) {
                    signupPassword.setError("Password is required");
                    signupPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    signupPassword.setError("Password must be at least 6 characters");
                    signupPassword.requestFocus();
                    return;
                }

                // Save credentials in SharedPreferences
                SharedPreferences sp = getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(SignupActivity.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();

                // Redirect to Login
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
