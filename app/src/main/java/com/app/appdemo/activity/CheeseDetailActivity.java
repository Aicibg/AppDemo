package com.app.appdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;
import com.app.appdemo.R;
import com.app.appdemo.Utils.Constants;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class CheeseDetailActivity extends BaseActivity {
   public static final String EXTRA_NAME="cheese_name";
    @BindView(R.id.backdrop)
    ImageView mImageView;
    @BindView(R.id.collaping_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_detail);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        final  String cheeseName=getIntent().getStringExtra(EXTRA_NAME);
        initToolBar();
        mCollapsingToolbarLayout.setTitle(cheeseName);
        Glide.with(this).load(Constants.getRandomCheeseDrawable()).fitCenter().into(mImageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
