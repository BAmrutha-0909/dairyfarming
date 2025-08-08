package com.example.dairyfarming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginBtn, signupRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        signupRedirect = findViewById(R.id.signupRedirect);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String pass = passwordInput.getText().toString().trim();

            // Gmail-only check
            String gmailPattern = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
            if (!email.matches(gmailPattern)) {
                emailInput.setError("Enter a valid Gmail address");
                emailInput.requestFocus();
                return;
            }

            // Password empty check
            if (pass.isEmpty()) {
                passwordInput.setError("Password is required");
                passwordInput.requestFocus();
                return;
            }

            // Retrieve stored credentials
            SharedPreferences sp = getSharedPreferences("user_data", MODE_PRIVATE);
            String savedEmail = sp.getString("email", "");
            String savedPass = sp.getString("password", "");

            if (email.equals(savedEmail) && pass.equals(savedPass)) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        signupRedirect.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class))
        );
    }
}
