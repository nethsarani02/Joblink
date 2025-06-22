package com.s23010174.joblink;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ChatPageActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private EditText messageInput;
    private View sendButton;
    private TextView onlineStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_page);

        chatContainer = findViewById(R.id.chat_container);
        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button_container);
        onlineStatus = findViewById(R.id.online_status);

        TextView userName = findViewById(R.id.user_name);
        TextView avatar = findViewById(R.id.avatar);

        String chatUsername = getIntent().getStringExtra("CHAT_USERNAME");
        if (chatUsername != null && !chatUsername.isEmpty()) {
            userName.setText(chatUsername);
            avatar.setText(String.valueOf(chatUsername.charAt(0)));
        }

        setOnlineStatusColor();

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void setOnlineStatusColor() {
        SpannableString text = new SpannableString("● Online");
        text.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        onlineStatus.setText(text);
    }

    private void sendMessage() {
        String messageText = messageInput.getText().toString().trim();
        if (TextUtils.isEmpty(messageText)) {
            return; // Don't send empty messages
        }

        // Create a new TextView for the message
        TextView messageView = new TextView(this);
        messageView.setText(messageText);
        messageView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        messageView.setBackgroundResource(R.drawable.chat_bubble_outgoing_bg);
        messageView.setPadding(32, 32, 32, 32);
        messageView.setTextSize(11f);

        // Set layout params to align to the right
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.END;
        params.setMargins(0, 0, 0, 16);
        messageView.setLayoutParams(params);

        // Add the new message to the chat container
        chatContainer.addView(messageView);

        // Clear the input field
        messageInput.setText("");
    }

    // Add your button click methods here. For example:
    // public void openSomePage(View view) {
    //     Intent intent = new Intent(this, SomePageActivity.class);
    //     startActivity(intent);
    // }
} 