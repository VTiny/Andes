package com.xiaoshao.andes.learning;

import android.support.v7.widget.DividerItemDecoration;

import com.xiaoshao.andes.base.BaseListFragment;
import com.xiaoshao.andes.list.SimpleListItemDecoration;

import java.util.List;

/**
 * Description: 测试分割线列表
 * Author: liuxiao
 * Date: 2019/3/19
 */
public class ItemDecorationLearningFragment extends BaseListFragment {

    @Override
    protected void onResponse(List response) {
        getRecyclerView().addItemDecoration(new SimpleListItemDecoration(getContext(), DividerItemDecoration.VERTICAL, getRecyclerView(), getAdapter()));
        super.onResponse(response);
    }

    @Override
    protected String getAssetDataPath() {
        return "data/list_item_decoration_learning.json";
    }
}
