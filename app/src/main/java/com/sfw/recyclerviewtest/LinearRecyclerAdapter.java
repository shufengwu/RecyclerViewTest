package com.sfw.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shufeng.Wu on 2016/12/13.
 */

public class LinearRecyclerAdapter extends RecyclerView.Adapter<LinearRecyclerAdapter.MyViewHolder> {


    private List<String> mDatas;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public List<String> getmDatas() {
        return this.mDatas;
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public LinearRecyclerAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public LinearRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearRecyclerAdapter.MyViewHolder holder = new LinearRecyclerAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final LinearRecyclerAdapter.MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        if (mOnItemClickListener != null) {
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    mOnItemClickListener.onItemClick(holder.tv, position);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
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

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
