package com.xiaoshao.andes.base;

import android.content.Context;
import android.net.Uri;

import com.xiaoshao.andes.bean.ListItemBean;
import com.xiaoshao.andes.scheme.SchemeUtils;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class CommonClickHandler {

    public static void onListItemClick(Context context, ListItemBean itemBean) {
        if (context == null || itemBean == null) {
            return;
        }
        context.startActivity(SchemeUtils.getAndesIntent(context, Uri.parse(itemBean.getScheme())));
    }

}
