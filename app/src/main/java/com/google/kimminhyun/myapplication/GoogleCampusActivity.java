package com.google.kimminhyun.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimminhyun on 7/16/15.
 */
public class GoogleCampusActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText mEditText;
    private ListView mListView;
    private List<ParseObject> mList = new ArrayList<ParseObject>();
    private BaseAdapter mAdapter;
    private ImageView mImageView;

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
                        fetchData();
                    }
                });

            }
        });

        View cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        mAdapter = new ImageAndTextAdapter();
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(mAdapter);

        mImageView = (ImageView) findViewById(R.id.image);
        fetchData();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteData = stream.toByteArray();

            final ParseFile file = new ParseFile("capture.jpg", byteData);
            file.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    ParseObject testObject = new ParseObject("TestObject");
                    testObject.put("image", file);
                    testObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(GoogleCampusActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                            fetchData();
                        }
                    });
                }
            });

            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void fetchData() {
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("TestObject");
        query1.whereExists("text");
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TestObject");
        query2.whereExists("image");
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(query1);
        queries.add(query2);
        ParseQuery<ParseObject> query = ParseQuery.or(queries);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                mList = list;
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    class ImageAndTextAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public ParseObject getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(GoogleCampusActivity.this, R.layout.image_and_text_view, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            ParseObject object = getItem(position);
            if (object.has("text")) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(object.getString("text"));
            } else {
                textView.setVisibility(View.GONE);
            }
            if (object.has("image")) {
                imageView.setVisibility(View.VISIBLE);
                ParseFile imageFile = object.getParseFile("image");
                Picasso.with(GoogleCampusActivity.this).load(imageFile.getUrl()).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
}
