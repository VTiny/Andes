package com.xiaoshao.andes.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.learning.AndesDividerItemDecoration;
import com.xiaoshao.andes.list.DividerStyle.Style;
import com.xiaoshao.andes.util.DataUtil;

import static com.xiaoshao.andes.list.DividerStyle.Style.NONE;

/**
 * Description: 封装基本列表的ItemDecoration，根据DividerStyle.Style 处理分割线样式
 * Author: liuxiao
 * Date: 2019/3/20
 */
public class SimpleListItemDecoration extends AndesDividerItemDecoration {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private BaseRecyclerViewAdapter mAdapter;

    public SimpleListItemDecoration(Context context, int orientation, RecyclerView recyclerView, BaseRecyclerViewAdapter adapter) {
        super(context, orientation);
        mContext = context;
        mRecyclerView = recyclerView;
        mAdapter = adapter;
    }

    @Override
    public Drawable getDrawable(View view) {
        if (mRecyclerView == null || mAdapter == null) {
            return null;
        }
        int position = mRecyclerView.getChildAdapterPosition(view);
        Style currBottomDividerStyle = getHolderBottomDividerStyle(getItemViewHolderClass(position));
        Style nextTopDividerStyle = getHolderTopDividerStyle(getItemViewHolderClass(position + 1));
        Style dividerStyle = currBottomDividerStyle.priority() > nextTopDividerStyle.priority() ? currBottomDividerStyle : nextTopDividerStyle;
        return getDrawableByStyle(mContext, dividerStyle);
    }

    private Class<? extends BaseRecyclerViewHolder> getItemViewHolderClass(int position) {
        if (DataUtil.isValid(mAdapter.getData(), position)) {
            return mAdapter.getItemViewHolderClass(mAdapter.getItemViewType(position));
        }
        return null;
    }

    private Drawable getDrawableByStyle(Context context, Style style) {
        switch (style) {
            case NONE:
                return null;
            case SIMPLE:
                return ContextCompat.getDrawable(context, R.drawable.base_list_simple_divider);
            case LARGE:
                return ContextCompat.getDrawable(context, R.drawable.base_list_simple_divider_large);
            default:
                return null;
        }
    }

    private Style getHolderTopDividerStyle(Class<? extends BaseRecyclerViewHolder> holderClass) {
        if (holderClass == null) {
            return NONE;
        }
        if (holderClass.isAnnotationPresent(DividerStyle.class)) {
            DividerStyle style = holderClass.getAnnotation(DividerStyle.class);
            if (style != null) {
                return style.top();
            }
        }
        return NONE;
    }

    private Style getHolderBottomDividerStyle(Class<? extends BaseRecyclerViewHolder> holderClass) {
        if (holderClass == null) {
            return NONE;
        }
        if (holderClass.isAnnotationPresent(DividerStyle.class)) {
            DividerStyle style = holderClass.getAnnotation(DividerStyle.class);
            if (style != null) {
                return style.bottom();
            }
        }
        return NONE;
    }

}
