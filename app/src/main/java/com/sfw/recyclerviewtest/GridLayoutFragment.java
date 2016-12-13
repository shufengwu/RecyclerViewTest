package com.sfw.recyclerviewtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;

/**
 * Created by Shufeng.Wu on 2016/12/11.
 */

public class GridLayoutFragment extends Fragment{
    RecyclerView recyclerView;
    private List<String> mDatas;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linearlayout,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        //adapter
        recyclerAdapter = new GridLayoutFragment.RecyclerAdapter();
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);
        initData();
        //添加item分隔线
        recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), 3));
        //默认ItemAnimator
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Slide
        //recyclerView.setItemAnimator(new SlideInLeftAnimator());
        //recyclerView.setItemAnimator(new SlideInRightAnimator());
        //recyclerView.setItemAnimator(new SlideInUpAnimator());
        //recyclerView.setItemAnimator(new SlideInDownAnimator());
        //recyclerView.setItemAnimator(new OvershootInLeftAnimator());
        //recyclerView.setItemAnimator(new OvershootInRightAnimator());

        //Scale
        //recyclerView.setItemAnimator(new ScaleInAnimator());
        //recyclerView.setItemAnimator(new ScaleInTopAnimator());
        //recyclerView.setItemAnimator(new ScaleInBottomAnimator());
        //recyclerView.setItemAnimator(new ScaleInLeftAnimator());
        //recyclerView.setItemAnimator(new ScaleInRightAnimator());

        //Cool
        //recyclerView.setItemAnimator(new LandingAnimator());

        //Flip
        //recyclerView.setItemAnimator(new FlipInTopXAnimator());
        //recyclerView.setItemAnimator(new FlipInBottomXAnimator());
        //recyclerView.setItemAnimator(new FlipInLeftYAnimator());
        //recyclerView.setItemAnimator(new FlipInRightYAnimator());

        //Fade
        //recyclerView.setItemAnimator(new FadeInAnimator());
        //FadeInDownAnimator
        //recyclerView.setItemAnimator(new FadeInDownAnimator());
        //FadeInUpAnimator
        //recyclerView.setItemAnimator(new FadeInUpAnimator());
        //FadeInLeftAnimator
        //recyclerView.setItemAnimator(new FadeInLeftAnimator());
        //FadeInRightAnimator
        recyclerView.setItemAnimator(new FadeInRightAnimator());
        return view;
    }
    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("" + (i + 1));
        }
    }

    public void addData(int position) {
        recyclerAdapter.addData(position);
    }

    public void removeData(int position) {
        recyclerAdapter.removeData(position);
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<GridLayoutFragment.RecyclerAdapter.MyViewHolder> {

        @Override
        public GridLayoutFragment.RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            GridLayoutFragment.RecyclerAdapter.MyViewHolder holder = new GridLayoutFragment.RecyclerAdapter.MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_recyclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(GridLayoutFragment.RecyclerAdapter.MyViewHolder holder, int position) {
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

        public void addData(int position) {
            if (position <= mDatas.size()) {
                mDatas.add(position, "New One");
                notifyItemInserted(position);
            }
        }

        public void removeData(int position) {
            if (position < mDatas.size()) {
                mDatas.remove(position);
                notifyItemRemoved(position);
            }
        }
    }
}
