package com.app.appdemo.activity;

import android.os.Bundle;

import com.app.appdemo.R;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initToolBar();
        String title=getIntent().getStringExtra("title");
        setTitle(title);
    }
}
