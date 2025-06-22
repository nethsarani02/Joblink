package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreatePageActivity extends AppCompatActivity {

    private TextInputEditText fullNameEditText, emailEditText, phoneEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox termsCheckbox;
    private Button createAccountButton;
    private TextView signInText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_page);

        mAuth = FirebaseAuth.getInstance();

        fullNameEditText = findViewById(R.id.full_name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        termsCheckbox = findViewById(R.id.terms_checkbox);
        createAccountButton = findViewById(R.id.create_account_btn);
        signInText = findViewById(R.id.already_have_account);

        createAccountButton.setOnClickListener(v -> createAccount());
        signInText.setOnClickListener(v -> startActivity(new Intent(CreatePageActivity.this, SignInActivity.class)));
    }

    private void createAccount() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String fullName = fullNameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            fullNameEditText.setError("Full name is required.");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required.");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            phoneEditText.setError("Phone number is required.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required.");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Please confirm your password.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match.");
            return;
        }
        if (!termsCheckbox.isChecked()) {
            Toast.makeText(this, "You must accept the terms and conditions.", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(CreatePageActivity.this, "Account created.", Toast.LENGTH_SHORT).show();
                        // You can now proceed to save other user details to a database if needed
                        startActivity(new Intent(CreatePageActivity.this, JobListingActivity.class));
                        finishAffinity(); // Clear all previous activities
                    } else {
                        Toast.makeText(CreatePageActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
} 