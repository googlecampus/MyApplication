package com.google.kimminhyun.myapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.parse.ParseBroadcastReceiver;

import de.greenrobot.event.EventBus;

/**
 * Custom receiver for customizing when push received.
 *
 * Created by kimminhyun on 8/6/15.
 */
public class CustomParsePushBroadcaseReceiver extends ParseBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        EventBus.getDefault().post(new LikeEvent());
    }
}
