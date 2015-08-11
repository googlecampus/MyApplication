package com.google.kimminhyun.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.parse.ParseBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Custom receiver for customizing when push received.
 *
 * Created by kimminhyun on 8/6/15.
 */
public class CustomParsePushBroadcaseReceiver extends ParseBroadcastReceiver {

    private static final int NOTIFICATION_ID = 999;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        EventBus.getDefault().post(new LikeEvent());

        Bundle extras = intent.getExtras();
        JSONObject jsonData = null;
        int count = 0;
        try {
            jsonData = new JSONObject(extras.getString("com.parse.Data"));
            count = jsonData.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.fox)
                        .setContentTitle("Liked! : " + count);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, GoogleCampusActivity.class);

        // Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
