package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.LinearLayout;

public class SearchPageActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageView searchIcon;
    private CardView jobCard1, jobCard2, jobCard3, jobCard4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        // Initialize views
        searchBar = findViewById(R.id.search_bar);
        searchIcon = findViewById(R.id.search_icon_title);
        jobCard1 = findViewById(R.id.job_card_1);
        jobCard2 = findViewById(R.id.job_card_2);
        jobCard3 = findViewById(R.id.job_card_3);
        jobCard4 = findViewById(R.id.job_card_4);

        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout messagesBtn = findViewById(R.id.messages_btn);
        LinearLayout profileBtn = findViewById(R.id.profile_btn);
        LinearLayout postJobBtn = findViewById(R.id.post_job_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPageActivity.this, JobListingActivity.class));
            }
        });

        messagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPageActivity.this, MessagesPageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPageActivity.this, ProfilePageActivity.class));
            }
        });

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPageActivity.this, PostJobActivity.class));
            }
        });

        // Set click listener for the search icon
        searchIcon.setOnClickListener(v -> performSearch());

        // Set click listeners for the job cards
        jobCard1.setOnClickListener(v -> openJobDetail("Restaurant Helper", "123"));
        jobCard2.setOnClickListener(v -> openJobDetail("Warehouse Assistant", "124"));
        jobCard3.setOnClickListener(v -> openJobDetail("Barista – Part-time", "125"));
        jobCard4.setOnClickListener(v -> openJobDetail("Part-time Sales Assistant", "126"));
    }

    private void performSearch() {
        String query = searchBar.getText().toString().trim();
        if (TextUtils.isEmpty(query)) {
            Utils.showToast(this, "Please enter a search term.");
        } else {
            // In a real app, you would perform a search here.
            // For now, just show a toast.
            Utils.showToast(this, "Searching for: " + query);
        }
    }

    /**
     * Called when the user taps on a search result to see details.
     * This method demonstrates passing data to the next activity.
     */
    private void openJobDetail(String title, String jobId) {
        Intent intent = new Intent(this, JobDetailPageActivity.class);
        // Pass data to the JobDetailPageActivity.
        // In a real app, you would pass the unique ID of the selected job.
        intent.putExtra("JOB_ID", jobId);
        intent.putExtra("JOB_TITLE", title);
        startActivity(intent);
    }
} 