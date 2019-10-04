package com.xiaoshao.andes.util;

import android.text.TextUtils;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/18
 */
public class DataUtil {

    public static <T> boolean isValid(T data) {
        return data != null;
    }

    public static <T> boolean isValid(T... data) {
        return data != null && data.length > 0;
    }

    public static boolean isValid(String data) {
        return !TextUtils.isEmpty(data);
    }

    public static boolean isValid(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean isValid(List list, int index) {
        return isValid(list) && index >= 0 && index < list.size();
    }

    public static <T> boolean isNotValid(T data) {
        return !isValid(data);
    }

    public static <T> boolean isNotValid(T... data) {
        return !isValid(data);
    }

    public static boolean isNotValid(String data) {
        return !isValid(data);
    }

    public static boolean isNotValid(List list) {
        return !isValid(list);
    }

    public static boolean isNotValid(List list, int index) {
        return !isValid(list, index);
    }

}
