package com.xiaoshao.andes.learning;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xiaoshao.andes.base.BaseListFragment;

/**
 * Description: 测试分割线列表
 * Author: liuxiao
 * Date: 2019/3/19
 */
public class ItemDecorationLearningFragment extends BaseListFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        getRecyclerView().setLayoutManager(layoutManager);
        getRecyclerView().addItemDecoration(new MyItemDecoration());
        //        getRecyclerView().addItemDecoration(new SimpleListItemDecoration(getContext(), DividerItemDecoration.VERTICAL, getRecyclerView(), getAdapter()));
    }

    @Override
    protected String getAssetDataPath() {
        return "data/list_item_decoration_learning.json";
    }

    private class MyItemDecoration extends GroupListItemDecoration {
        @Override
        protected void initStyle(Paint textPaint) {
            textPaint.setTextSize(39);
            textPaint.setColor(Color.RED);
            textPaint.setTextAlign(Paint.Align.CENTER);
            setItemHeight(100);
            setReverse(true);
        }

        @Override
        protected String getGroupTitle(int position) {
//            if (position % 2 == 1) {
                return String.format("Group: %s", position);
//            }
//            return super.getGroupTitle(position);
        }
    }
}
