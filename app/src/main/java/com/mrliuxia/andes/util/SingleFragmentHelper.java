package com.mrliuxia.andes.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mrliuxia.andes.base.SingleFragmentActivity;

/**
 * Author: liuxiao
 * Created: 2019/2/11 01:25
 * Description:
 */
public class SingleFragmentHelper {

    /**
     * 获取一个启动SingleFragmentActivity Intent
     *
     * @param context
     * @param fragmentName fragment类名
     * @param fragmentTag  fragment tag
     * @param fragmentArgs fragment 启动参数
     * @param clazz        为SingleFragmentActivity.class或者SingleFragmentActivity子类.class
     * @return
     */
    public static Intent getStartIntent(Context context, String fragmentName, String fragmentTag, Bundle fragmentArgs) {
        return getStartIntent(context, fragmentName, fragmentTag, fragmentArgs, SingleFragmentActivity.class);
    }

    public static Intent getStartIntent(Context context, String fragmentName, String fragmentTag,
                                        Bundle fragmentArgs, Class clazz) {
        Intent intent = new Intent(context, clazz);
        putSingleBundle(intent, fragmentName, fragmentTag, fragmentArgs);
        return intent;
    }

    public static Intent getStartIntent(Context context, Class clazz, Bundle fragmentArgs) {
        return getStartIntent(context, clazz.getName(), clazz.getSimpleName(), fragmentArgs);
    }

    public static void putSingleBundle(Intent intent, String fragmentName, String fragmentTag, Bundle fragmentArgs) {
        if (intent == null) {
            return;
        }
        intent.putExtra(SingleFragmentActivity.PARAM_NAME, fragmentName);
        intent.putExtra(SingleFragmentActivity.PARAM_TAG, fragmentTag);
        intent.putExtra(SingleFragmentActivity.PARAM_ARGS, fragmentArgs);
    }
}
