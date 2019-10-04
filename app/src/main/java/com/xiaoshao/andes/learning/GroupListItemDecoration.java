package com.xiaoshao.andes.learning;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

/**
 * Description: RecyclerView支持分组的ItemDecoration，支持根据位置获取分组标题、设置基本样式
 * Author: liuxiao
 * Data: 2019-09-18
 *
 * @blame: liuxiao
 */
public class GroupListItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;           // 文字的Paint
    private int mItemHeight;        // ItemDecoration高度
    private boolean mIsReverse;     // 是否反转
    private int mPadding;           // 左右Padding

    public GroupListItemDecoration() {
        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.LEFT);
        initStyle(mPaint);
    }

    /**
     * 可在此方法中设置文字样式、ItemDecoration高度等
     */
    protected void initStyle(Paint textPaint) {
    }

    /**
     * 根据在列表中的位置获取组名称
     */
    protected String getGroupTitle(int position) {
        return null;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (TextUtils.isEmpty(getGroupTitle(position))) {
            getGroupInsideOffset(outRect, view, parent, state);
        } else {
            getGroupOutsideOffset(outRect, view, parent, state);
        }
        getExtraOffset(outRect, view, parent, state, position);
    }

    /**
     * 组内部Item之间的Offset
     */
    protected void getGroupInsideOffset(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

    }

    /**
     * 组与组之间的Offset
     */
    protected void getGroupOutsideOffset(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        setRect(outRect, 0, mItemHeight);
    }

    protected void getExtraOffset(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state, int position) {

    }

    protected void setRect(@NonNull Rect rect, int x, int y) {
        if (mIsReverse) {
            rect.set(0, y, x, 0);
        } else {
            rect.set(0, 0, x, y);
        }
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        for (int i = 0, n = parent.getChildCount(); i < n; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String groupTitle = getGroupTitle(position);
            if (!TextUtils.isEmpty(groupTitle) && mPaint != null && mPaint.getFontMetricsInt() != null) {
                int centerY = mIsReverse ? (view.getTop() - mItemHeight / 2) : (view.getBottom() + mItemHeight / 2);
                canvas.drawText(groupTitle, getTextXPosition(view, mPaint.getTextAlign()),
                        centerY - (mPaint.getFontMetricsInt().bottom + mPaint.getFontMetricsInt().top) / 2f, mPaint);
            }
        }
    }

    public void setReverse(boolean reverse) {
        mIsReverse = reverse;
    }

    public void setItemHeight(int height) {
        mItemHeight = height;
    }

    public void setPadding(int padding) {
        mPadding = padding;
    }

    private int getTextXPosition(View itemView, Paint.Align align) {
        int x = itemView.getLeft();
        if (align != null) {
            switch (align) {
                case LEFT:
                    x = itemView.getLeft() + mPadding;
                    break;
                case CENTER:
                    x = (itemView.getLeft() + itemView.getRight()) / 2;
                    break;
                case RIGHT:
                    x = itemView.getRight() - mPadding;
                    break;
            }
        }
        return x;
    }

}
