package com.xiaoshao.andes.main;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.xiaoshao.andes.base.BaseListFragment;
import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.bean.ListBean;
import com.xiaoshao.andes.simple.SimpleListHolder;

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

        final List<ListBean> list = new ArrayList<>();
        ListBean bean1 = new ListBean();
        bean1.setTitle("Expandable Index");
        list.add(bean1);
        ListBean bean2 = new ListBean();
        bean2.setTitle("MotionLayout Learning");
        list.add(bean2);

        getAdapter().updateDataAndNotify(list, true);
    }

}
