package com.sfw.recyclerviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shufeng.Wu on 2016/12/11.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;
    private int column;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }

    public GridItemDecoration(Context context, int column) {
        this.column = column;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c,parent);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {

        int columnSize = parent.getChildCount();
        for (int i = 0; i < columnSize; i++) {
            if (i % column != column - 1) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() - params.rightMargin;
                int right = left + mDivider.getIntrinsicHeight();
                int top = child.getTop() + params.topMargin;
                int bottom = child.getBottom() - params.bottomMargin;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {

        int columnSize = parent.getChildCount();
        for (int i = 0; i < columnSize; i++) {
            if ((i + 1) <= columnSize / column * column) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getLeft() + params.leftMargin;
                int right = child.getRight() - params.rightMargin;
                int top = child.getBottom() - params.bottomMargin;
                int bottom = child.getBottom() + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

}
