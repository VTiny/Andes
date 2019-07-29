package com.xiaoshao.andes.bean;

import com.xiaoshao.andes.base.IListBean;

/**
 * Description: 首页列表bean
 * Author: liuxiao
 * Date: 2019/2/19
 */
public class ListItemBean implements IListBean {

    private String title;
    private String scheme;
    private String style;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
