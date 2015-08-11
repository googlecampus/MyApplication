package com.google.kimminhyun.myapplication;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * Created by kimminhyun on 8/11/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "D5RlFIWu0Vjf0quz7R4wo5P4IYSLgVvappZ3RwbL", "SiqgMDRNizUr0exYifzaTGKuZfLviVUXpAV9Z85O");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("like_number");
    }
}
