package com.mrliuxia.andes.simple;

import androidx.annotation.NonNull;
import android.view.ViewGroup;

import com.mrliuxia.andes.base.BaseRecyclerViewAdapter;
import com.mrliuxia.andes.base.BaseRecyclerViewHolder;
import com.mrliuxia.andes.base.IListBean;
import com.mrliuxia.andes.bean.ListItemBean;
import com.mrliuxia.andes.list.LargeDividerListHolder;
import com.mrliuxia.andes.list.ListProtocol;
import com.mrliuxia.andes.list.RecyclerViewItemType;
import com.mrliuxia.andes.util.DataUtil;

import java.lang.reflect.Constructor;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class SimpleListAdapter extends BaseRecyclerViewAdapter<IListBean> {

    @Override
    public int getItemViewType(int position) {
        IListBean item = getItem(position);
        if (item instanceof ListItemBean) {
            String style = ((ListItemBean) item).getStyle();
            if (DataUtil.isValid(style)) {
                switch (style) {
                    case ListProtocol.STYLE_LARGE_DIVIDER:
                        return RecyclerViewItemType.ITEM_TYPE_LARGE_DIVIDER;
                }
            }
        }
        return RecyclerViewItemType.ITEM_TYPE_NORMAL;
    }

    @Override
    public Class<? extends BaseRecyclerViewHolder> getItemViewHolderClass(int itemViewType) {
        switch (itemViewType) {
            case RecyclerViewItemType.ITEM_TYPE_NORMAL:
                return SimpleListHolder.class;
            case RecyclerViewItemType.ITEM_TYPE_LARGE_DIVIDER:
                return LargeDividerListHolder.class;
        }
        return null;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Class<? extends BaseRecyclerViewHolder> holderClass = getItemViewHolderClass(viewType);
        try {
            Constructor<? extends BaseRecyclerViewHolder> holderConstructor = holderClass.getDeclaredConstructor(ViewGroup.class);
            return holderConstructor.newInstance(viewGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleListHolder(viewGroup);

    }

}
