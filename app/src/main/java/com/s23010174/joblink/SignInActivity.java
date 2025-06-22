package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private Button signInButton;
    private TextView joinNowTextView, forgotPasswordTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in_btn);
        joinNowTextView = findViewById(R.id.join_now);
        forgotPasswordTextView = findViewById(R.id.forgot_password);

        signInButton.setOnClickListener(v -> signIn());
        joinNowTextView.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, CreatePageActivity.class)));
        forgotPasswordTextView.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class)));
    }

    private void signIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required.");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "Sign in successful.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, JobListingActivity.class));
                        finishAffinity();
                    } else {
                        Toast.makeText(SignInActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
} 