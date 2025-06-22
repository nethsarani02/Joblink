package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.Button;

public class JobDetailPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_detail_page);

        AppCompatButton locationButton = findViewById(R.id.location_on_map_btn);
        Button messageButton = findViewById(R.id.message_btn);
        Button applyButton = findViewById(R.id.apply_now_btn);

        // Get data from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String jobId = extras.getString("JOB_ID");
            String jobTitle = extras.getString("JOB_TITLE");

            // Use the data
            if (jobTitle != null) {
                TextView jobTitleTextView = findViewById(R.id.job_title);
                jobTitleTextView.setText(jobTitle);
            }
            // Show a toast with the Job ID to confirm it's received
            Utils.showToast(this, "Viewing Job ID: " + jobId);
        }

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailPageActivity.this, MapPageActivity.class);
                startActivity(intent);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailPageActivity.this, ChatPageActivity.class);
                intent.putExtra("CHAT_USERNAME", "Rajesh Food Corner");
                startActivity(intent);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailPageActivity.this, JobApplyPageActivity.class);
                intent.putExtra("JOB_TITLE", "Delivery Driver Needed");
                startActivity(intent);
            }
        });
    }
} 