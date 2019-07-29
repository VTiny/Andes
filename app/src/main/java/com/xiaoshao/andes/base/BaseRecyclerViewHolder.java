package com.xiaoshao.andes.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoshao.andes.list.DividerStyle;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
@DividerStyle
public class BaseRecyclerViewHolder<D> extends RecyclerView.ViewHolder {

    private SparseArray<View> mViewCache = new SparseArray<>();
    private int mPosition;
    private D mData;

    public BaseRecyclerViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    public final void bindView(D itemData, int position) {
        mData = itemData;
        mPosition = position;
        bindView(itemData);
    }

    public final void bindView(D itemData, int position, @NonNull List<Object> payloads) {
        mData = itemData;
        mPosition = position;
        bindView(itemData, payloads);
    }

    protected void bindView(D itemData) {

    }

    protected void bindView(D itemData, @NonNull List<Object> payloads) {

    }

    protected View getView(@IdRes int id) {
        View view = mViewCache.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViewCache.append(id, view);
        }
        return view;
    }

    protected Context getContext() {
        return itemView.getContext();
    }

}
