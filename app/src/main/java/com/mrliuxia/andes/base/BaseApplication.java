package com.mrliuxia.andes.base;

import android.app.Application;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/4
 */
public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
