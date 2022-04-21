package com.mrliuxia.andes.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mrliuxia.andes.bean.ListItemBean;
import com.mrliuxia.andes.scheme.SchemeUtils;
import com.mrliuxia.andes.util.DataUtil;

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
        Intent intent = SchemeUtils.getAndesIntent(context, Uri.parse(itemBean.getScheme()));
        if (DataUtil.isValid(intent)) {
            context.startActivity(intent);
        }
    }

}
