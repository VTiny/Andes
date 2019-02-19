package com.xiaoshao.andes.simple;

import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.base.IListBean;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
public class SimpleListHolder extends BaseRecyclerViewHolder {

    public SimpleListHolder(ViewGroup parent) {
        super(parent, R.layout.simple_list_holder);
    }

    @Override
    protected void bindView(Object itemData) {
        super.bindView(itemData);
        ((TextView) getView(R.id.title)).setText(getAdapterPosition()+"");
    }
}
