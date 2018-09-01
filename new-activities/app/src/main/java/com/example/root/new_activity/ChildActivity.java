package com.example.root.new_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.content.Intent.getIntent;

public class ChildActivity extends AppCompatActivity {
    TextView mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        mDisplayText = (TextView) findViewById(R.id.tv_display);

        Intent intentThatStartThisActivity = getIntent();
        if(intentThatStartThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String textEntered = intentThatStartThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayText.setText(textEntered);
        }
    }
}
