package com.xiaoshao.andes.util;

import com.xiaoshao.andes.base.BaseApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class StringUtil {

    public static String getLocalJsonFile(String fileName) {
        String result = null;
        try {
            InputStream is = BaseApplication.getInstance().getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            result = new String(b, "UTF-8");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
