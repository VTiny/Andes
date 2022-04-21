package com.mrliuxia.andes.simple;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrliuxia.andes.R;
import com.mrliuxia.andes.base.BaseRecyclerViewHolder;
import com.mrliuxia.andes.base.CommonClickHandler;
import com.mrliuxia.andes.base.IListBean;
import com.mrliuxia.andes.bean.ListItemBean;
import com.mrliuxia.andes.list.DividerStyle;

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
