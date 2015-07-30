package com.google.kimminhyun.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by kimminhyun on 7/16/15.
 */
public class GoogleCampusActivity extends Activity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_campus);
        mEditText = (EditText) findViewById(R.id.edit_text);
        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject testObject = new ParseObject("TestObject");
                testObject.put("text", mEditText.getText().toString());
                testObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(GoogleCampusActivity.this, "Save successful", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        query.whereExists("text");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for (int i = 0; i < list.size(); ++i) {
                    ParseObject object = list.get(i);
                    Toast.makeText(GoogleCampusActivity.this, object.getString("text"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
