package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_page);

        TextView backButton = findViewById(R.id.back_button);
        TextView signInLink = findViewById(R.id.sign_in_link);
        Button sendCodeButton = findViewById(R.id.send_code_button);

        backButton.setOnClickListener(v -> finish());

        signInLink.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        sendCodeButton.setOnClickListener(v -> {
            // For now, just show a toast. Later, this will send a verification code.
            Utils.showToast(ForgotPasswordActivity.this, "Verification code sent!");
        });
    }
} 