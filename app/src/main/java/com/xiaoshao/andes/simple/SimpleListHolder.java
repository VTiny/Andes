package com.xiaoshao.andes.simple;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.base.IListBean;
import com.xiaoshao.andes.bean.ListBean;
import com.xiaoshao.andes.util.SingleFragmentHelper;
import com.xiaoshao.andes.working.ExpandableIndexFragment;

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
    protected void bindView(IListBean itemData) {
        super.bindView(itemData);
        ((TextView) getView(R.id.title)).setText(((ListBean) itemData).getTitle());
        if (getAdapterPosition() == 0) {
            getView(R.id.divider).setVisibility(View.GONE);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (getAdapterPosition()) {
                    case 0:
                        intent = SingleFragmentHelper.getStartIntent(getContext(), ExpandableIndexFragment.class, null);
                        getContext().startActivity(intent);
                        break;

                }
            }
        });
    }
}
