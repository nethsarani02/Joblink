package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class PostJobActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_job_page);

        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout searchBtn = findViewById(R.id.search_btn);
        LinearLayout messagesBtn = findViewById(R.id.messages_btn);
        LinearLayout profileBtn = findViewById(R.id.profile_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostJobActivity.this, JobListingActivity.class));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostJobActivity.this, SearchPageActivity.class));
            }
        });

        messagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostJobActivity.this, MessagesPageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostJobActivity.this, ProfilePageActivity.class));
            }
        });
    }
} 