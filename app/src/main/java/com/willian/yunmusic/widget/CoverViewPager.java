package com.willian.yunmusic.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 歌曲封面ViewPager
 */

public class CoverViewPager extends ViewPager {

    private PointF mPoint = new PointF();

    public CoverViewPager(Context context) {
        super(context);
    }

    public CoverViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPoint.x = ev.getX();
                mPoint.y = ev.getY();
                if (getChildCount() > 1) {
                    // 有内容，多于1个时，通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(ev.getX() - mPoint.x, ev.getY() - mPoint.y) < (float) 5.0) {
                    onSingleTouch(this);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 回调接口
     */
    private OnSingleTouchListener mListener;

    public interface OnSingleTouchListener {
        void onSingleTouch(View v);
    }

    public void setOnSingleTouchListener(
            OnSingleTouchListener onSingleTouchListener) {
        this.mListener = onSingleTouchListener;
    }

    private void onSingleTouch(View v) {
        if (mListener != null) {
            mListener.onSingleTouch(v);
        }
    }
}
