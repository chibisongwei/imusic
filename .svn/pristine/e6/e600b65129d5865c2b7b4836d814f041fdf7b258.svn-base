package com.willian.yunmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 高度自适应的ListView
 */
public class WrapHeightListView extends ListView {

    public WrapHeightListView(Context context) {
        super(context);
    }

    public WrapHeightListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * 重新绘制ListView的高度
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
