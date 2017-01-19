package com.willian.yunmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 高度自适应的GridView
 */
public class WrapGridView extends GridView {

    public WrapGridView(Context context) {
        super(context);
    }

    public WrapGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * 重新绘制GridView的高度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // int类型有32位，最高两位表示模式
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
