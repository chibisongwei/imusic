package com.willian.yunmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 图片轮播控件
 */
public class RollView extends FrameLayout {

    public RollView(Context context) {
        super(context);
    }

    public RollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 解决滑动冲突
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 父View不消耗Event事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
