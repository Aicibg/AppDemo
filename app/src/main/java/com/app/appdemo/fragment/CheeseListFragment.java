package com.app.appdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.appdemo.R;
import com.app.appdemo.Utils.Constants;
import com.app.appdemo.adapter.SampleRecycleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * @FileName: com.app.appdemo.fragment.CheeseListFragment.java
 * Description:
 * Created by donghao on 2016/7/1.
 */
public class CheeseListFragment extends Fragment{
   @BindView(R.id.rlv_recyclerview)
    RecyclerView mRecyclerView;
    private SampleRecycleAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_cheeslist,null);
        setUpRecycleView(view);
        return view;
    }

    private void setUpRecycleView(View view) {
           mRecyclerView= (RecyclerView) view.findViewById(R.id.rlv_recyclerview);
          mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
           mAdapter=new SampleRecycleAdapter(getActivity(),getRandumSublist(Constants.scheessString,30));
           mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getRandumSublist(String[] scheessString, int amount) {
        ArrayList<String> list=new ArrayList<>(amount);
        Random random=new Random();
        while(list.size()<amount){
            list.add(scheessString[random.nextInt(scheessString.length)]);
        }
        return list;
    }
}
