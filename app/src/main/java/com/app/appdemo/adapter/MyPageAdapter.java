package com.app.appdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: com.app.appdemo.adapter.MyPageAdapter.java
 * Description:viewPage适配器
 * Created by donghao on 2016/7/1.
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments=new ArrayList<Fragment>();
    private final List<String> fragmentTitles=new ArrayList<String>();

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String title){
        mFragments.add(fragment);
        fragmentTitles.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
