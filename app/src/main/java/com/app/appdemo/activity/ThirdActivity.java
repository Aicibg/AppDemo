package com.app.appdemo.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.app.appdemo.R;
import com.app.appdemo.adapter.RollpageAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;

public class ThirdActivity extends BaseActivity {
    @BindView(R.id.roll_view_pagr)
    RollPagerView mRollPagerView;
    private RollpageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initPage();

    }

    private void initPage() {
        mRollPagerView.setAnimationDurtion(500);//透明度
        mRollPagerView.setPlayDelay(3000);//播放时间间隔
        adapter=new RollpageAdapter();
        mRollPagerView.setAdapter(adapter);
        mRollPagerView.setHintView(new ColorPointHintView(this, Color.BLUE,Color.WHITE));
    }
}
