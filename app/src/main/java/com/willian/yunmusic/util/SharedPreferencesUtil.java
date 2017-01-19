package com.willian.yunmusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences工具类
 */

public class SharedPreferencesUtil {

    private static final String PLAY_MODE = "play_mode";

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferences getPreferneces(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mSharedPreferences;
    }

    /**
     * 保存整型值
     */
    private static void putInt(Context context, String key, int value) {
        getPreferneces(context).edit().putInt(key, value).commit();
    }

    /**
     * 获取整型值
     *
     * @param key
     * @return
     */
    private static int getInt(Context context, String key, int defaultVal) {
        return getPreferneces(context).getInt(key, defaultVal);
    }

    /**
     * 保存字符串
     */
    private static void putString(Context context, String key, String value) {
        getPreferneces(context).edit().putString(key, value).commit();
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    private static String getString(Context context, String key) {
        return getPreferneces(context).getString(key, null);
    }

    /**
     * 设置歌曲播放模式
     *
     * @param context
     * @param mode
     */
    public static void setPlayMode(Context context, int mode) {
        putInt(context, PLAY_MODE, mode);
    }

    /**
     * 获取歌曲播放模式
     *
     * @param context
     * @return
     */
    public static int getPlayMode(Context context) {
        return getInt(context, PLAY_MODE, 0);
    }
}
