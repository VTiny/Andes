package com.xiaoshao.andes.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
public abstract class BaseRecyclerViewAdapter<D> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<D> mData = new ArrayList<>();

    public List<D> getData() {
        // 使用unmodifiablelist，避免外界获取到data的引用对数据做更改
        return Collections.unmodifiableList(mData);
    }

    public D getItem(int position) {
        if (position < 0 || position > mData.size()) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void updateData(List<D> data, boolean isRefresh) {
        synchronized (mData) {
            if (isRefresh) {
                mData.clear();
            }
            if (data != null && !data.isEmpty()) {
                mData.addAll(data);
            }
        }
    }

    public void updateDataAndNotify(List<D> data, boolean isRefresh) {
        int originCount = getItemCount();
        updateData(data, isRefresh);
        if (isRefresh) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeChanged(originCount, data == null ? 0 : data.size());
        }
    }

    public void removeItemAndNotify(int position) {
        if (position >= 0 && position < mData.size()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateItemAndNotify(int position, D data) {
        if (position >= 0 && position < mData.size()) {
            mData.set(position, data);
            notifyItemChanged(position);
        }
    }

    public void insertItemAndNotify(int position, D data) {
        if (position >= 0 && position <= mData.size()) {
            mData.add(position, data);
            notifyItemInserted(position);
        }
    }

    @NonNull
    @Override
    public abstract BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType);

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public Class<? extends BaseRecyclerViewHolder> getItemViewHolderClass(int itemViewType) {
        return null;
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseRecyclerViewHolder viewHolder, int position) {
        onBindItemViewHolder(viewHolder, position);
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseRecyclerViewHolder viewHolder, int position, @NonNull List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            onBindItemViewHolder(viewHolder, position);
        } else {
            onBindItemViewHolder(viewHolder, position, payloads);
        }
    }

    protected void onBindItemViewHolder(BaseRecyclerViewHolder<D> viewHolder, int position) {
        viewHolder.bindView(getItem(position), position);
    }

    protected void onBindItemViewHolder(BaseRecyclerViewHolder<D> viewHolder, int position, List<Object> payloads) {
        viewHolder.bindView(getItem(position), position, payloads);
    }
}
