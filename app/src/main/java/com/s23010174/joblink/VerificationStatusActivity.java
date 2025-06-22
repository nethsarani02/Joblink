package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationStatusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_status);
    }

    /**
     * Called when the user taps the button to go back to the home page.
     */
    public void goToHome(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        // Clear the back stack and start a new task
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
} 