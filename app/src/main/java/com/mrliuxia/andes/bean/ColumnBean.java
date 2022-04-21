package com.mrliuxia.andes.bean;

import com.mrliuxia.andes.base.IListBean;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class ColumnBean implements IListBean {

    private String column;
    private List<ListItemBean> data;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<ListItemBean> getData() {
        return data;
    }

    public void setData(List<ListItemBean> data) {
        this.data = data;
    }
}
