package com.google.kimminhyun.myapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.parse.ParseBroadcastReceiver;

/**
 * Custom receiver for customizing when push received.
 *
 * Created by kimminhyun on 8/6/15.
 */
public class CustomParsePushBroadcaseReceiver extends ParseBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getExtras() != null) {
            Toast.makeText(context, "intent extra : " + intent.getExtras().toString(), Toast.LENGTH_LONG).show();
        }
        if (intent.getDataString() != null) {
            Toast.makeText(context, "intent data : " + intent.getDataString(), Toast.LENGTH_LONG).show();
        }
    }
}
