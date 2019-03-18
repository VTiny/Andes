package com.xiaoshao.andes.simple;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class SimpleListAdapter<D> extends BaseRecyclerViewAdapter<D> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new SimpleListHolder(viewGroup);
    }

}
