package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView letsGoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Initialize Views
        letsGoButton = findViewById(R.id.lets_go_btn);

        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignInActivity
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Called when the user clicks the "Create Account" button/link.
     * Navigates to the Create Account screen.
     */
    public void openCreateAccount(View view) {
        Intent intent = new Intent(this, CreatePageActivity.class);
        startActivity(intent);
    }
}
