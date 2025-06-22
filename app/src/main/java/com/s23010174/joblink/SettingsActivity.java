package com.s23010174.joblink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        RelativeLayout editProfileContainer = findViewById(R.id.edit_profile_container);
        RelativeLayout changePasswordContainer = findViewById(R.id.change_password_container);
        SwitchMaterial jobAlertsSwitch = findViewById(R.id.jobAlertsSwitch);
        SwitchMaterial messageNotificationsSwitch = findViewById(R.id.messageNotificationsSwitch);

        editProfileContainer.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        changePasswordContainer.setOnClickListener(v -> {
            Toast.makeText(SettingsActivity.this, "Change Password clicked", Toast.LENGTH_SHORT).show();
            // Intent to open Change Password activity can be added here
        });

        jobAlertsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(SettingsActivity.this, "Job Alerts " + status, Toast.LENGTH_SHORT).show();
        });

        messageNotificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(SettingsActivity.this, "Message Notifications " + status, Toast.LENGTH_SHORT).show();
        });
    }

    // Add your button click methods here.
} 