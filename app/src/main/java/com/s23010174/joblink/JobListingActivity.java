package com.s23010174.joblink;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class JobListingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    // Shake detection
    private float accel;
    private float accelCurrent;
    private float accelLast;
    private long lastRefreshTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Initialize shake detection variables
        accel = 0.00f;
        accelCurrent = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;

        // Setup Listeners
        setupNavigationListeners();
        setupClickListeners();
    }

    private void setupClickListeners() {
        TextView notificationBell = findViewById(R.id.notification_bell_icon);
        notificationBell.setOnClickListener(v -> {
            Intent intent = new Intent(JobListingActivity.this, NotificationPageActivity.class);
            startActivity(intent);
        });

        TextView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(JobListingActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Job Card Click Listeners
        ConstraintLayout jobCard1 = findViewById(R.id.job_card_1);
        ConstraintLayout jobCard2 = findViewById(R.id.job_card_2);
        ConstraintLayout jobCard3 = findViewById(R.id.job_card_3);
        ConstraintLayout jobCard4 = findViewById(R.id.job_card_4);
        ConstraintLayout jobCard5 = findViewById(R.id.job_card_5);

        View.OnClickListener openJobDetail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJobDetail();
            }
        };

        jobCard1.setOnClickListener(openJobDetail);
        jobCard2.setOnClickListener(openJobDetail);
        jobCard3.setOnClickListener(openJobDetail);
        jobCard4.setOnClickListener(openJobDetail);
        jobCard5.setOnClickListener(openJobDetail);

        LinearLayout searchBtn = findViewById(R.id.search_btn);
        LinearLayout messagesBtn = findViewById(R.id.messages_btn);
        LinearLayout profileBtn = findViewById(R.id.profile_btn);
        LinearLayout postJobBtn = findViewById(R.id.post_job_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListingActivity.this, SearchPageActivity.class));
            }
        });

        messagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListingActivity.this, MessagesPageActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListingActivity.this, ProfilePageActivity.class));
            }
        });

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JobListingActivity.this, PostJobActivity.class));
            }
        });
    }

    private void setupNavigationListeners() {
        findViewById(R.id.home_btn).setOnClickListener(v -> { /* Already on Home */ });
        findViewById(R.id.search_btn).setOnClickListener(v -> startActivity(new Intent(this, SearchPageActivity.class)));
        findViewById(R.id.post_job_btn).setOnClickListener(v -> startActivity(new Intent(this, PostJobActivity.class)));
        findViewById(R.id.messages_btn).setOnClickListener(v -> startActivity(new Intent(this, MessagesPageActivity.class)));
        findViewById(R.id.profile_btn).setOnClickListener(v -> startActivity(new Intent(this, ProfilePageActivity.class)));
    }

    private void openJobDetail() {
        Intent intent = new Intent(this, JobDetailPageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            accelLast = accelCurrent;
            accelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accelCurrent - accelLast;
            accel = accel * 0.9f + delta; // Low-pass filter

            if (accel > 5) { // Threshold for sensitivity
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastRefreshTime > 1000) { // 1 second cooldown
                    lastRefreshTime = currentTime;
                    refreshPage();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Can be ignored for this feature
    }

    private void refreshPage() {
        Toast.makeText(this, "Page Refreshed", Toast.LENGTH_SHORT).show();
        // Here you would add the logic to re-fetch and update your job listings
    }
} 