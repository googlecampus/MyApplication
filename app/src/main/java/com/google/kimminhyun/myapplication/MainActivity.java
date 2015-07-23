package com.google.kimminhyun.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private View mTextView1;
    private View mTextView2;
    private TextView mTextView3;
    private ImageView mImage1;
    private View mImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = findViewById(R.id.text1);
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGoogleCampusActivity();
            }
        });

        mTextView2 = findViewById(R.id.text2);
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGoogleCampusActivity();
            }
        });

        mTextView3 = (TextView) findViewById(R.id.text3);
        mImage1 = (ImageView) findViewById(R.id.image1);
        mImage2 = findViewById(R.id.image2);
        mTextView3.setText("Word Changed");
        mImage1.setImageResource(R.drawable.fox);
        mImage2.setVisibility(View.GONE);
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
