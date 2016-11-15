package com.gyf.toolutils4a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * The type Sp utils.
 */
public class SPUtils {

    /**
     * 存储SharedPreferences值
     *
     * @param context  the context
     * @param filename the filename
     * @param key      the key
     * @param object   the object
     */
    public static void setSP(Context context, String filename, String key, Object object) {
        String type = object.getClass().getSimpleName();
        String packageName = context.getPackageName();
        SharedPreferences sp = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        if ("String".equals(type)) {
            edit.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            edit.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            edit.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            edit.putLong(key, (Long) object);
        }
        edit.apply();
    }

    /**
     * 获取SharedPreferences值
     *
     * @param context       the context
     * @param key           the key
     * @param defaultObject the default object
     * @return the sp
     */
    public static Object getSp(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        String packageName = context.getPackageName();
        SharedPreferences sp = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 清除所有的SP值
     *
     * @param context the context
     */
    public static void cleanAllSP(Context context) {
        String packageName = context.getPackageName();
        SharedPreferences sp = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
