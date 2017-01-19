package com.willian.yunmusic.util;

import android.content.Context;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * Handler工具类
 */

public class HandlerUtil extends Handler {

    private static HandlerUtil mInstance = null;

    private WeakReference<Context> mActivityReference;

    private HandlerUtil(Context context) {
        mActivityReference = new WeakReference<>(context);
    }

    public static HandlerUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HandlerUtil(context);
        }
        return mInstance;
    }
}
