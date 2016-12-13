package com.sfw.recyclerviewtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shufeng.Wu on 2016/12/11.
 */

public class StaggeredGridLayoutFragment extends Fragment{
    RecyclerView recyclerView;
    private List<String> mDatas;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linearlayout,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
        //adapter
        recyclerAdapter = new StaggeredGridLayoutFragment.RecyclerAdapter();
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);
        initData();
        //添加item分隔线
        //recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), 3));
        return view;
    }
    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 101; i++) {
            mDatas.add("" + (i + 1));
        }
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<StaggeredGridLayoutFragment.RecyclerAdapter.MyViewHolder> {

        @Override
        public StaggeredGridLayoutFragment.RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            StaggeredGridLayoutFragment.RecyclerAdapter.MyViewHolder holder = new StaggeredGridLayoutFragment.RecyclerAdapter.MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_recyclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(StaggeredGridLayoutFragment.RecyclerAdapter.MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.item_recyelerview_text);
            }
        }
    }
}
