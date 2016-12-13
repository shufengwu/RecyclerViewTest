package com.sfw.recyclerviewtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;

/**
 * Created by Shufeng.Wu on 2016/12/11.
 */

public class LinearLayoutFragment extends Fragment {
    RecyclerView recyclerView;
    private List<String> mDatas;
    private LinearRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linearlayout, container, false);
        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter
        recyclerAdapter = new LinearRecyclerAdapter(getActivity(),mDatas);
        //设置adapter
        recyclerView.setAdapter(recyclerAdapter);

        //添加item分隔线
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity(), MyItemDecoration.VERTICAL_LIST));

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

        //第三方ItemAnimator:右侧滑入滑出并缩放
        //recyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(recyclerView));

        recyclerView.getItemAnimator().setAddDuration(300);
        recyclerView.getItemAnimator().setRemoveDuration(300);
        recyclerView.getItemAnimator().setMoveDuration(300);
        recyclerView.getItemAnimator().setChangeDuration(300);

        //开源库RecyclerItemClickSupport实现OnItemClickListener
        /*RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getActivity(), "OnItemClickListener "+position, Toast.LENGTH_SHORT).show();
            }
        }).setOnItemLongClickListener(new RecyclerItemClickSupport.OnItemLongClickListener() {
            //开源库RecyclerItemClickSupport实现OnItemLongClickListener
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getActivity(),"OnItemLongClickListener() "+position,Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

        recyclerAdapter.setOnItemClickLitener(new LinearRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"onItemClick "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),"onItemLongClick "+position,Toast.LENGTH_SHORT).show();
                recyclerAdapter.removeData(position);
            }
        });


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

    /*public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


        @Override
        public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_recyclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
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
    }*/
}
