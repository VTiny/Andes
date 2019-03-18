package com.xiaoshao.andes.util;

import android.content.res.Resources;
import android.util.TypedValue;

import com.xiaoshao.andes.base.BaseApplication;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/4
 */
public class SystemUtil {

    public static float dp2px(float dp) {
        return dp2px(BaseApplication.getInstance().getResources(), dp);
    }

    public static float dp2px(Resources resources, float dp) {
        if (resources == null) {
            return 0F;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }


}
