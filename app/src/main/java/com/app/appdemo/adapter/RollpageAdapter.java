package com.app.appdemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.appdemo.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * @FileName: com.app.appdemo.adapter.RollpageAdapter.java
 * Description:引导页viewPage适配器
 * Created by donghao on 2016/6/30.
 */
public class RollpageAdapter extends StaticPagerAdapter{
    private int[] imgs={R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3,R.mipmap.guide4};

    public RollpageAdapter() {
        super();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setImageResource(imgs[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return (int) object;
    }
}
