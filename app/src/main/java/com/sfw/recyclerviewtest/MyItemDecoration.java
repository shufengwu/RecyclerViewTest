package com.sfw.recyclerviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shufeng.Wu on 2016/12/11.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == HORIZONTAL_LIST){

        }else{
            outRect.set(0,0,mDivider.getIntrinsicHeight(),mDivider.getIntrinsicHeight());
        }
    }

    public MyItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (this.mOrientation == HORIZONTAL_LIST) {

        } else {
            drawVertical(c,parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top;
        int bottom;
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount-1;i++){
            View child = parent.getChildAt(i);
            //RecyclerView v = new RecyclerView(parent.getContext());
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            top = child.getBottom()+params.bottomMargin;
            bottom = top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }

        /*View child = parent.getChildAt(0);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
        top = child.getTop();
        bottom = top+mDivider.getIntrinsicHeight();
        mDivider.setBounds(left,top,right,bottom);
        mDivider.draw(c);

        right = parent.getPaddingLeft()+mDivider.getIntrinsicHeight();
        top = parent.getPaddingTop();
        bottom = top + parent.getHeight();
        mDivider.setBounds(left,top,right,bottom);
        mDivider.draw(c);

        right = parent.getWidth() - parent.getPaddingRight();
        left = right-mDivider.getIntrinsicHeight();
        mDivider.setBounds(left,top,right,bottom);
        mDivider.draw(c);*/

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
