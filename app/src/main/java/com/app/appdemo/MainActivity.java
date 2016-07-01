package com.app.appdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.appdemo.activity.BaseActivity;
import com.app.appdemo.activity.SecondActivity;
import com.app.appdemo.activity.ThirdActivity;
import com.app.appdemo.adapter.MyPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawer_layout)
     DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.toolbar)
    Toolbar mtoolbar;
    @BindView(R.id.viewpage)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    private MyPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        setTitle("主页");

        if(mNavView!=null){
            setUpDrawerCotent(mNavView);
        }
        if(mtoolbar!=null){
            mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
        if(mViewPager!=null){
            setUpViewPage();
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPage() {
        adapter=new MyPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(adapter);
    }

    //侧滑菜单点击监听
    private void setUpDrawerCotent(NavigationView navView) {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                switch (item.getItemId()){
                    case R.id.nav_home:
                          mDrawerLayout.closeDrawers();
                      break;
                      case R.id.nav_message:
                          intent.putExtra("title","消息中心");
                          startActivity(intent);
                          overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
                      break;
                      case R.id.nav_search:
                          Intent intent1=new Intent(MainActivity.this, ThirdActivity.class);
                          intent1.putExtra("title","搜索");
                          startActivity(intent1);
                          overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);
                       break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
  //toolbar menu菜单点击监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
              case R.id.item_main:
                 start(MainActivity.this);
                  finish();
              break;
              case R.id.item_personinfo:

              break;
        }
        return true;
    }

    @OnClick(R.id.fab)
    public void showSnackBAr(){
        Snackbar.make(ButterKnife.findById(this,R.id.fab),"弹出Sbachbar",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
    }

     public static void start(Activity activity){
        activity.startActivity(new Intent(activity,MainActivity.class));
    }
}
