package com.xiaoshao.andes.simple;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.base.CommonClickHandler;
import com.xiaoshao.andes.base.IListBean;
import com.xiaoshao.andes.bean.ListItemBean;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
public class SimpleListHolder extends BaseRecyclerViewHolder<IListBean> {

    public SimpleListHolder(ViewGroup parent) {
        super(parent, R.layout.simple_list_holder);
    }

    @Override
    protected void bindView(final IListBean itemData) {
        super.bindView(itemData);
        ((TextView) getView(R.id.title)).setText(((ListItemBean) itemData).getTitle());
        if (getAdapterPosition() == 0) {
            getView(R.id.divider).setVisibility(View.GONE);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonClickHandler.onListItemClick(getContext(), ((ListItemBean) itemData));// TODO: 2019/3/18
            }
        });
    }
}
