package com.willian.yunmusic.util;

import android.util.Log;

import com.willian.yunmusic.constant.Constant;

/**
 * 日志统一管理工具类
 */
public class LoggerUtil {

    /**
     * 显示INFO级别的日志
     *
     * @param TAG
     * @param msg
     */
    public static void showLog(String TAG, String msg) {
        if (!Constant.IS_SHOW_LOG) {
            return;
        }
        // 默认显示INFO级别的日志信息
        showLog(TAG, msg, Log.INFO);
    }

    /**
     * 显示某个级别的日志
     *
     * @param TAG
     * @param msg
     * @param level
     */
    public static void showLog(String TAG, String msg, int level) {
        if (!Constant.IS_SHOW_LOG) {
            return;
        }
        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
        }
    }

}
