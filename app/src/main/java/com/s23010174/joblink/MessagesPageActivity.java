package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MessagesPageActivity extends AppCompatActivity {

    private RelativeLayout messageItem1, messageItem2, messageItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_page);

        messageItem1 = findViewById(R.id.message_item_1);
        messageItem2 = findViewById(R.id.message_item_2);
        messageItem3 = findViewById(R.id.message_item_3);

        View.OnClickListener openChatListener = v -> {
            Intent intent = new Intent(MessagesPageActivity.this, ChatPageActivity.class);
            // In a real app, you would pass a conversation ID here
            // intent.putExtra("CONVERSATION_ID", v.getTag().toString());
            startActivity(intent);
        };

        messageItem1.setOnClickListener(openChatListener);
        messageItem2.setOnClickListener(openChatListener);
        messageItem3.setOnClickListener(openChatListener);

        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout searchBtn = findViewById(R.id.search_btn);
        LinearLayout profileBtn = findViewById(R.id.profile_btn);
        LinearLayout postJobBtn = findViewById(R.id.post_job_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagesPageActivity.this, JobListingActivity.class));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagesPageActivity.this, SearchPageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagesPageActivity.this, ProfilePageActivity.class));
            }
        });

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagesPageActivity.this, PostJobActivity.class));
            }
        });
    }
} 