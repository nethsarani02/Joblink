package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationPageActivity extends AppCompatActivity {

    private EditText[] codeInputs;
    private TextView expiresText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_page);

        // This is a bit brute-force, a proper implementation would use a loop over an array of IDs
        codeInputs = new EditText[]{
                findViewById(R.id.code_inputs).findViewWithTag("code1"),
                findViewById(R.id.code_inputs).findViewWithTag("code2"),
                findViewById(R.id.code_inputs).findViewWithTag("code3"),
                findViewById(R.id.code_inputs).findViewWithTag("code4"),
                findViewById(R.id.code_inputs).findViewWithTag("code5"),
                findViewById(R.id.code_inputs).findViewWithTag("code6")
        };

        expiresText = findViewById(R.id.expires_text);
        Button verifyButton = findViewById(R.id.verify_button);
        Button resendButton = findViewById(R.id.resend_button);
        TextView tryDifferentText = findViewById(R.id.try_different_text);

        setupCodeInputs();
        startTimer();

        verifyButton.setOnClickListener(v -> {
            // Collect the code and verify it
            Toast.makeText(this, "Verifying code...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, VerificationStatusActivity.class);
            startActivity(intent);
        });

        resendButton.setOnClickListener(v -> Toast.makeText(this, "Resending code...", Toast.LENGTH_SHORT).show());

        tryDifferentText.setOnClickListener(v -> Toast.makeText(this, "Try a different method clicked", Toast.LENGTH_SHORT).show());
    }

    private void setupCodeInputs() {
        for (int i = 0; i < codeInputs.length; i++) {
            final int currentIndex = i;
            codeInputs[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && currentIndex < codeInputs.length - 1) {
                        codeInputs[currentIndex + 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void startTimer() {
        new CountDownTimer(164000, 1000) { // 2 minutes 44 seconds
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                expiresText.setText(String.format("Code expires in %02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                expiresText.setText("Code expired");
            }
        }.start();
    }
} 