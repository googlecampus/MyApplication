package com.google.kimminhyun.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends ActionBarActivity {

    private View mTextView1;
    private View mButton1;
    private TextView mTextView3;
    private ImageView mImage1;
    private ImageView mImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = findViewById(R.id.text1);
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextView1.isSelected()) {
                    mTextView1.setSelected(false);
                    mImage1.setImageResource(R.drawable.fox);
                } else {
                    mTextView1.setSelected(true);
                    mImage1.setImageResource(R.drawable.panda);
                }
            }
        });

        mButton1 = findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGoogleCampusActivity();
            }
        });

        mTextView3 = (TextView) findViewById(R.id.text3);
        mImage1 = (ImageView) findViewById(R.id.image1);
        mImage2 = (ImageView) findViewById(R.id.image2);
        mTextView3.setText("Word Changed");
        mImage2.setImageResource(R.drawable.panda);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "D5RlFIWu0Vjf0quz7R4wo5P4IYSLgVvappZ3RwbL", "SiqgMDRNizUr0exYifzaTGKuZfLviVUXpAV9Z85O");
    }

    private void startGoogleCampusActivity() {
        Intent intent = new Intent(MainActivity.this, GoogleCampusActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startGoogleCampusActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
