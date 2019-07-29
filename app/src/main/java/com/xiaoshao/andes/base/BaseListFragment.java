package com.xiaoshao.andes.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.bean.ColumnBean;
import com.xiaoshao.andes.simple.SimpleListAdapter;
import com.xiaoshao.andes.util.DataUtil;
import com.xiaoshao.andes.util.JsonUtil;
import com.xiaoshao.andes.util.StringUtil;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/2/18
 */
public abstract class BaseListFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private BaseRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = createAdapter();
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerViewAdapter() {
                @NonNull
                @Override
                public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new BaseRecyclerViewHolder(parent, android.R.layout.activity_list_item);
                }
            };
        }
    }

    protected BaseRecyclerViewAdapter createAdapter() {
        return new SimpleListAdapter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.base_recycler_view_layout;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAssetLocal();
    }

    protected void onResponse(List response) {
        if (DataUtil.isValid(getAdapter())) {
            getAdapter().updateDataAndNotify(response, true);
        }
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    protected void loadAssetLocal() {
        if (getAdapter() == null) {
            return;
        }
        if (DataUtil.isValid(getAssetDataPath())) {
            ColumnBean columnBean = JsonUtil.fromJson(StringUtil.getLocalJsonFile(getAssetDataPath()), ColumnBean.class);
            onResponse(columnBean.getData());
        }
    }

    protected String getAssetDataPath() {
        return null;
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    protected BaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }
}
