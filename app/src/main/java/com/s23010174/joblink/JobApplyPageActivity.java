package com.s23010174.joblink;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobApplyPageActivity extends AppCompatActivity {

    private EditText coverLetterInput, interestInput, startDateInput, contactNumberInput, emailInput;
    private LinearLayout attachResumeBox;
    private CheckBox confirmCheckbox;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_apply_page);

        coverLetterInput = findViewById(R.id.cover_letter_input);
        interestInput = findViewById(R.id.interest_input);
        startDateInput = findViewById(R.id.start_date_input);
        contactNumberInput = findViewById(R.id.contact_number_input);
        emailInput = findViewById(R.id.email_input);
        attachResumeBox = findViewById(R.id.attach_resume_box);
        confirmCheckbox = findViewById(R.id.confirm_checkbox);
        sendButton = findViewById(R.id.send_button);

        TextView positionTitle = findViewById(R.id.position_title);

        String jobTitle = getIntent().getStringExtra("JOB_TITLE");
        if (jobTitle != null && !jobTitle.isEmpty()) {
            positionTitle.setText(jobTitle);
        }

        attachResumeBox.setOnClickListener(v -> {
            // TODO: Implement file picker logic
            Toast.makeText(JobApplyPageActivity.this, "Attach Resume clicked", Toast.LENGTH_SHORT).show();
        });

        sendButton.setOnClickListener(v -> {
            if (!confirmCheckbox.isChecked()) {
                Toast.makeText(JobApplyPageActivity.this, "Please confirm that all information is accurate.", Toast.LENGTH_SHORT).show();
                return;
            }
            // TODO: Implement submission logic
            Toast.makeText(JobApplyPageActivity.this, "Application Sent!", Toast.LENGTH_SHORT).show();
        });
    }
} 