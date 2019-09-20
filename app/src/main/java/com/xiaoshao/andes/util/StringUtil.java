package com.xiaoshao.andes.util;

import android.text.TextUtils;

import com.xiaoshao.andes.base.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<String> getLabelValues(String s, String label) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(String.format("<%s>(.*?)</%s>", label, label));
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }

}
