package com.sfw.recyclerviewtest;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;

/**
 * Created by Shufeng.Wu on 2016/12/14.
 */

public class CustomLinearLayoutFragment extends Fragment {
    RecyclerView recyclerView;
    private List<String> mDatas;
    private List<Integer> mIcons;
    private int[] icon_ids = new int[]{
            android.R.drawable.ic_delete,
            android.R.drawable.ic_input_add,
            android.R.drawable.ic_menu_myplaces,
            android.R.drawable.stat_sys_phone_call_forward,
            android.R.drawable.ic_menu_share
    };
    private CustomLinearLayoutFragment.RecyclerAdapter recyclerAdapter;
    final String TAG = "CustomLinearLayoutFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initData();
        initIcon();
        View view = inflater.inflate(R.layout.fragment_linearlayout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerAdapter = new CustomLinearLayoutFragment.RecyclerAdapter();
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addItemDecoration(new MyItemDecoration(getActivity(), MyItemDecoration.VERTICAL_LIST));

        recyclerView.setItemAnimator(new FadeInRightAnimator());

        RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getActivity(), "OnItemClickListener " + position, Toast.LENGTH_SHORT).show();
            }
        }).setOnItemLongClickListener(new RecyclerItemClickSupport.OnItemLongClickListener() {
            //开源库RecyclerItemClickSupport实现OnItemLongClickListener
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getActivity(), "OnItemLongClickListener() " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }

    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("RecyclerView测试用 " + (i + 1));
        }
    }

    @SuppressLint("LongLogTag")
    public void initIcon() {
        Log.i(TAG,"initIcon");
        mIcons = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            for(int j=0;j<icon_ids.length;j++){
                mIcons.add(icon_ids[j]);
            }
        }
    }

    public void addData(int position) {
        recyclerAdapter.addData(position);
    }

    public void removeData(int position) {
        recyclerAdapter.removeData(position);
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<CustomLinearLayoutFragment.RecyclerAdapter.MyViewHolder> {

        @Override
        public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CustomLinearLayoutFragment.RecyclerAdapter.MyViewHolder holder = new CustomLinearLayoutFragment.RecyclerAdapter.MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.custom_item_recyclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
            holder.iv.setImageResource(mIcons.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }



        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            ImageView iv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.item_recyelerview_text);
                iv = (ImageView) itemView.findViewById(R.id.item_icon);
            }
        }

        public void addData(int position) {
            if (position <= mDatas.size()) {
                mDatas.add(position, "New One");
                mIcons.add(position,icon_ids[(int)(Math.random()*5)]);
                notifyItemInserted(position);
            }
        }

        public void removeData(int position) {
            if (position < mDatas.size()) {
                mDatas.remove(position);
                mIcons.remove(position);
                notifyItemRemoved(position);
            }
        }
    }
}
