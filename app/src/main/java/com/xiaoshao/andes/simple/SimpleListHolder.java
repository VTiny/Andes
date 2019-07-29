package com.xiaoshao.andes.simple;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.base.CommonClickHandler;
import com.xiaoshao.andes.base.IListBean;
import com.xiaoshao.andes.bean.ListItemBean;
import com.xiaoshao.andes.list.DividerStyle;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
@DividerStyle
public class SimpleListHolder extends BaseRecyclerViewHolder<IListBean> {

    public SimpleListHolder(ViewGroup parent) {
        super(parent, R.layout.simple_list_holder);
    }

    @Override
    protected void bindView(final IListBean itemData) {
        super.bindView(itemData);
        itemView.setBackground(getContext().getResources().getDrawable(R.drawable.base_list_item_bg));
        ((TextView) getView(R.id.title)).setText(((ListItemBean) itemData).getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonClickHandler.onListItemClick(getContext(), ((ListItemBean) itemData));// TODO: 2019/3/18
            }
        });
    }

}
