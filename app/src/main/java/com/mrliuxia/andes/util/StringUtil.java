package com.mrliuxia.andes.util;

import android.text.TextUtils;

import com.mrliuxia.andes.base.BaseApplication;

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

    /**
     * 获取字符串内包含的<xml>标签的值
     * 如: getLabelValues("hello<comp>Netease</comp>News", "comp")
     * 结果：["Netease"]的List
     */
    public static List<String> getLabelValues(String s, String label) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(label)) {
            return null;
        }
        Pattern pattern = Pattern.compile(wrapLabel(label));
        Matcher matcher = pattern.matcher(s);
        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values;
    }

    public static List<CharSequence> getLabelValues(CharSequence s, String label) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(label)) {
            return null;
        }
        Pattern pattern = Pattern.compile(wrapLabel(label));
        Matcher matcher = pattern.matcher(s);
        List<CharSequence> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values;
    }

    /**
     * 替换字符串中第一个被<xml>标签包裹起来的字符串
     * 如：replaceFirstLabelByValue("hello<comp>Netease</comp>News", "comp", "NTES")
     * 结果：helloNTESNews
     */
    public static String replaceFirstLabelByValue(String s, String name, String value) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(name) || TextUtils.isEmpty(value)) {
            return s;
        }
        return s.replaceFirst(wrapLabel(name), value);
    }
    /**
     * 替换字符串中所有被<xml>标签包裹起来的字符串
     */
    public static String replaceAllLabelByValue(String s, String name, String value) {
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(name) || TextUtils.isEmpty(value)) {
            return s;
        }
        return s.replaceAll(wrapLabel(name), value);
    }

    public static String wrapLabel(String name) {
        return wrapLabel(name, null);
    }

    public static String wrapLabel(String name, String value) {
        if (value == null) {
            value = "(.*?)";
        }
        return String.format("<%s>%s</%s>", name, value, name);
    }

}
