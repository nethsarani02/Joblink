package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, EditProfileActivity.class));
            }
        });

        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout searchBtn = findViewById(R.id.search_btn);
        LinearLayout messagesBtn = findViewById(R.id.messages_btn);
        LinearLayout postJobBtn = findViewById(R.id.post_job_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, JobListingActivity.class));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, SearchPageActivity.class));
            }
        });

        messagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, MessagesPageActivity.class));
            }
        });

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePageActivity.this, PostJobActivity.class));
            }
        });
    }
} 