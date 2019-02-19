package com.xiaoshao.andes.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseFragment;
import com.xiaoshao.andes.base.BaseListFragment;
import com.xiaoshao.andes.base.BaseRecyclerViewAdapter;
import com.xiaoshao.andes.base.BaseRecyclerViewHolder;
import com.xiaoshao.andes.simple.SimpleListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: liuxiao
 * Created: 2019/1/12 18:29
 * Description:
 */
public class MainHomeTabFragment extends BaseListFragment {

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

        final List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        getAdapter().updateDataAndNotify(list, true);
    }
}
