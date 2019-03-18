package com.xiaoshao.andes.main;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.xiaoshao.andes.base.BaseListFragment;
import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.bean.ColumnBean;
import com.xiaoshao.andes.bean.ListItemBean;
import com.xiaoshao.andes.simple.SimpleListHolder;
import com.xiaoshao.andes.util.DataUtil;
import com.xiaoshao.andes.util.JsonUtil;
import com.xiaoshao.andes.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: liuxiao
 * Created: 2019/1/15 11:46
 * Description:
 */
public class MainWorkingTabFragment extends BaseListFragment {

    @Override
    protected BaseRecyclerViewAdapter createAdapter() {
        return new BaseRecyclerViewAdapter() {
            @NonNull
            @Override
            public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                return new SimpleListHolder(viewGroup);
            }
        };
    }

    @Override
    protected void afterViewCreated() {
        super.afterViewCreated();
        ColumnBean columnBean = JsonUtil.fromJson(StringUtil.getLocalJsonFile("data/list_working.json"), ColumnBean.class);
        if (DataUtil.isValid(columnBean) && DataUtil.isValid(columnBean.getData()) && getAdapter() != null) {
            getAdapter().updateDataAndNotify(columnBean.getData(), true);
        }
    }

}
