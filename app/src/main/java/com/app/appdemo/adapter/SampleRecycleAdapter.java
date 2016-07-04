package com.app.appdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.appdemo.MainActivity;
import com.app.appdemo.R;
import com.app.appdemo.Utils.Constants;
import com.app.appdemo.activity.CheeseDetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @FileName: com.app.appdemo.adapter.SampleRecycleAdapter.java
 * Description:
 * Created by donghao on 2016/7/1.
 */
public  class SampleRecycleAdapter extends RecyclerView.Adapter<SampleRecycleAdapter.ViewHolder> {
    private TypedValue mTypedValue=new TypedValue();
    private int mBackground;
    private List<String> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public String mBoundString;
        public  View mView;
        public  ImageView mImageView;
        public  TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            mImageView= (ImageView) itemView.findViewById(R.id.civ_avater);
            mTextView= (TextView) itemView.findViewById(R.id.tv_text1);
        }

        @Override
        public String toString() {
            return super.toString()+" '"+mTextView.getText();
        }
    }

    public String getValueAt(int position){
        return mValues.get(position);
    }

    public SampleRecycleAdapter(Context context, List<String> values) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground,mTypedValue,true);
        mBackground=mTypedValue.resourceId;
        mValues = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.page_list_item,parent,false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mBoundString=mValues.get(position);
        holder.mTextView.setText(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent=new Intent(context, CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.EXTRA_NAME,holder.mBoundString);
                context.startActivity(intent);
            }
        });

        Glide.with(holder.mImageView.getContext())
                .load(Constants.getRandomCheeseDrawable())
                .fitCenter().into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
