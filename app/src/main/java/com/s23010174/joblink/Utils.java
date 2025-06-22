package com.s23010174.joblink;

import android.content.Context;
import android.widget.Toast;

/**
 * A utility class for common helper functions.
 */
public class Utils {

    /**
     * Shows a short toast message.
     * @param context The context to show the toast in.
     * @param message The message to show.
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
} 