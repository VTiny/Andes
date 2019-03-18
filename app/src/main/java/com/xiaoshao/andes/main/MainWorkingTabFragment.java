package com.xiaoshao.andes.main;

import com.xiaoshao.andes.base.BaseListFragment;
import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.bean.ColumnBean;
import com.xiaoshao.andes.bean.ListItemBean;
import com.xiaoshao.andes.simple.SimpleListAdapter;
import com.xiaoshao.andes.util.DataUtil;
import com.xiaoshao.andes.util.JsonUtil;
import com.xiaoshao.andes.util.StringUtil;

/**
 * Author: liuxiao
 * Created: 2019/1/15 11:46
 * Description:
 */
public class MainWorkingTabFragment extends BaseListFragment {

    @Override
    protected BaseRecyclerViewAdapter createAdapter() {
        return new SimpleListAdapter<ListItemBean>();
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
