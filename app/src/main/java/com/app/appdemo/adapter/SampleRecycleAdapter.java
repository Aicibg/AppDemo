package com.app.appdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @FileName: com.app.appdemo.adapter.SampleRecycleAdapter.java
 * Description:
 * Created by donghao on 2016/7/1.
 */
public class SampleRecycleAdapter extends RecyclerView.Adapter {
    private TypedValue mTypedValue=new TypedValue();
    private int mBackground;
    private List<String> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public String mBoundString;
        public  View mView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
