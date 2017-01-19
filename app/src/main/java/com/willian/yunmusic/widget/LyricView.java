package com.willian.yunmusic.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.LyricRow;

import java.util.List;

/**
 * 歌词显示的自定义View
 */

public class LyricView extends View implements ILyricView {

    /**
     * 所有的歌词
     ***/
    private List<LyricRow> mLrcRows;
    /**
     * 无歌词数据的时候 显示的默认文字
     **/
    private static final String DEFAULT_TEXT = "暂时没有歌词";
    /**
     * 默认文字的字体大小
     **/
    private static final float SIZE_FOR_DEFAULT_TEXT = 50;

    /**
     * 画高亮歌词的画笔
     ***/
    private Paint mPaintForHighLightLrc;
    /**
     * 高亮歌词的默认字体大小
     ***/
    private static final float DEFAULT_SIZE_FOR_HIGHT_LIGHT_LRC = 43;
    /**
     * 高亮歌词当前的字体大小
     ***/
    private float mCurSizeForHightLightLrc = DEFAULT_SIZE_FOR_HIGHT_LIGHT_LRC;
    /**
     * 高亮歌词的默认字体颜色
     **/
    private static final int DEFAULT_COLOR_FOR_HIGHT_LIGHT_LRC = 0xffffffff;

    /**
     * 高亮歌词当前的字体颜色
     **/
    private int mCurColorForHightLightLrc = DEFAULT_COLOR_FOR_HIGHT_LIGHT_LRC;

    /**
     * 画其他歌词的画笔
     ***/
    private Paint mPaintForOtherLrc;
    /**
     * 其他歌词的默认字体大小
     ***/
    private static final float DEFAULT_SIZE_FOR_OTHER_LRC = 43;
    /**
     * 其他歌词当前的字体大小
     ***/
    private float mCurSizeForOtherLrc = DEFAULT_SIZE_FOR_OTHER_LRC;
    /**
     * 其他歌词的默认字体颜色
     **/
    private static final int DEFAULT_COLOR_FOR_OTHER_LRC = 0x80ffffff;
    /**
     * 其他歌词当前的字体颜色
     **/
    private int mCurColorForOtherLrc = DEFAULT_COLOR_FOR_OTHER_LRC;

    /**
     * 画时间线的画笔
     ***/
    private Paint mPaintForTimeLine;
    /***
     * 时间线的颜色
     **/
    private static final int COLOR_FOR_TIME_LINE = 0xff999999;
    /**
     * 时间文字大小
     **/
    private static final int SIZE_FOR_TIME = 35;
    /**
     * 是否画时间线
     **/
    private boolean mIsDrawTimeLine = false;

    /**
     * 歌词间默认的行距
     **/
    private static final float DEFAULT_PADDING = 50;
    /**
     * 歌词当前的行距
     **/
    private float mCurPadding = DEFAULT_PADDING;

    /**
     * 歌词的最大缩放比例
     **/
    public static final float MAX_SCALING_FACTOR = 1.5f;
    /**
     * 歌词的最小缩放比例
     **/
    public static final float MIN_SCALING_FACTOR = 0.5f;
    /**
     * 默认缩放比例
     **/
    private static final float DEFAULT_SCALING_FACTOR = 1.0f;
    /**
     * 歌词的当前缩放比例
     **/
    private float mCurScalingFactor = DEFAULT_SCALING_FACTOR;

    /**
     * 实现歌词竖直方向平滑滚动的辅助对象
     **/
    private Scroller mScroller;
    /***
     * 移动一句歌词的持续时间
     **/
    private static final int DURATION_FOR_LRC_SCROLL = 1500;
    /***
     * 停止触摸时 如果View需要滚动 时的持续时间
     **/
    private static final int DURATION_FOR_ACTION_UP = 400;

    /**
     * 控制文字缩放的因子
     **/
    private float mCurFraction = 0;

    private int mTouchSlop;

    private Bitmap arrowBitmap;

    private int mTotleDrawRow;

    /**
     * 当前高亮歌词的行号
     **/
    private int mCurRow = -1;
    /**
     * 上一次的高亮歌词的行号
     **/
    private int mLastRow = -1;

    /**
     * 高亮歌词当前的起始X轴绘制坐标
     **/
    private float mCurTextXForHighLightLrc;

    /**
     * 事件的第一次的y坐标
     **/
    private float firstY;
    /**
     * 事件的上一次的y坐标
     **/
    private float lastX;

    private float lastY;

    /**
     * 是否可拖动歌词
     **/
    private boolean canDrag = false;

    /**
     * 控制歌词水平滚动的属性动画
     ***/
    private ValueAnimator mAnimator;

    public LyricView(Context context) {
        super(context);
        init(context);
    }

    public LyricView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化画笔等
     */
    @Override
    public void init(Context context) {
        mScroller = new Scroller(getContext());
        mPaintForHighLightLrc = new Paint();
        mPaintForHighLightLrc.setColor(mCurColorForHightLightLrc);
        mPaintForHighLightLrc.setTextSize(mCurSizeForHightLightLrc);
        mPaintForHighLightLrc.setAntiAlias(true);

        mPaintForOtherLrc = new Paint();
        mPaintForOtherLrc.setColor(mCurColorForOtherLrc);
        mPaintForOtherLrc.setTextSize(mCurSizeForOtherLrc);
        mPaintForOtherLrc.setAntiAlias(true);

        mPaintForTimeLine = new Paint();
        mPaintForTimeLine.setColor(COLOR_FOR_TIME_LINE);
        mPaintForTimeLine.setTextSize(SIZE_FOR_TIME);

        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 30;
        options.inTargetDensity = 30;
        arrowBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.lrc_arrow, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLrcRows == null || mLrcRows.size() == 0) {
            //画默认的显示文字
            mPaintForOtherLrc.setTextSize(SIZE_FOR_DEFAULT_TEXT);
            float textWidth = mPaintForOtherLrc.measureText(DEFAULT_TEXT);
            float textX = (getWidth() - textWidth) / 2;
            canvas.drawText(DEFAULT_TEXT, textX, getHeight() / 2, mPaintForOtherLrc);
            return;
        }
        if (mTotleDrawRow == 0) {
            //初始化将要绘制的歌词行数
            mTotleDrawRow = (int) (getHeight() / (mCurSizeForOtherLrc + mCurPadding)) + 4;
        }
        // 因为不需要将所有歌词画出来
        int minRaw = mCurRow - (mTotleDrawRow - 1) / 2;
        int maxRaw = mCurRow + (mTotleDrawRow - 1) / 2;
        // 处理上边界
        minRaw = Math.max(minRaw, 0);
        // 处理下边界
        maxRaw = Math.min(maxRaw, mLrcRows.size() - 1);
        //实现渐变的最大歌词行数
        int count = Math.max(maxRaw - mCurRow, mCurRow - minRaw);
        if (count == 0) {
            return;
        }
        //两行歌词间字体颜色变化的透明度
        int alpha = (0xFF - 0x11) / count;
        //画出来的第一行歌词的y坐标
        float rowY = getHeight() / 2 + minRaw * (mCurSizeForOtherLrc + mCurPadding);
        for (int i = minRaw; i <= maxRaw; i++) {
            if (i == mCurRow) {
                //因为有缩放效果，所有需要动态设置歌词的字体大小
                float textSize = mCurSizeForOtherLrc + (mCurSizeForHightLightLrc - mCurSizeForOtherLrc) * mCurFraction;
                mPaintForHighLightLrc.setTextSize(textSize);
                // 获取到高亮歌词
                String text = mLrcRows.get(i).getContent();
                // 用画笔测量歌词的宽度
                float textWidth = mPaintForHighLightLrc.measureText(text);
                if (textWidth > getWidth()) {
                    //如果歌词宽度大于view的宽，则需要动态设置歌词的起始x坐标，以实现水平滚动
                    canvas.drawText(text, mCurTextXForHighLightLrc, rowY, mPaintForHighLightLrc);
                } else {
                    //如果歌词宽度小于view的宽，则让歌词居中显示
                    float textX = (getWidth() - textWidth) / 2;
                    canvas.drawText(text, textX, rowY, mPaintForHighLightLrc);
                }
            } else {
                //画高亮歌词的上一句
                if (i == mLastRow) {
                    //因为有缩放效果，所有需要动态设置歌词的字体大小
                    float textSize = mCurSizeForHightLightLrc - (mCurSizeForHightLightLrc - mCurSizeForOtherLrc) * mCurFraction;
                    mPaintForOtherLrc.setTextSize(textSize);
                } else {
                    mPaintForOtherLrc.setTextSize(mCurSizeForOtherLrc);
                }
                String text = mLrcRows.get(i).getContent();
                float textWidth = mPaintForOtherLrc.measureText(text);
                float textX = (getWidth() - textWidth) / 2;
                //如果计算出的textX为负数，将textX置为0(实现：如果歌词宽大于view宽，则居左显示，否则居中显示)
                textX = Math.max(textX, 0);
                canvas.drawText(text, textX, rowY, mPaintForOtherLrc);
            }
            //计算出下一行歌词绘制的y坐标
            rowY += mCurSizeForOtherLrc + mCurPadding;
        }

        // 画时间线和时间
        if (mIsDrawTimeLine) {
            float y = getHeight() / 2 + getScrollY();
            float x = getWidth();
            canvas.drawBitmap(arrowBitmap, -20, y - 41, null);
            canvas.drawText(mLrcRows.get(mCurRow).getStartTimeStr().substring(0, 5), x - 105, y + 13, mPaintForTimeLine);
            canvas.drawLine(60, y, getWidth() - 110, y, mPaintForTimeLine);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mLrcRows == null || mLrcRows.size() == 0) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                firstY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!canDrag) {
                    if (Math.abs(event.getRawY() - firstY) > mTouchSlop && Math.abs(event.getRawY() - firstY) > Math.abs(event.getRawX() - lastX)) {
                        canDrag = true;
                        mIsDrawTimeLine = true;
                        mScroller.forceFinished(true);
                        stopScrollLrc();
                        mCurFraction = 1;
                    }
                    lastY = event.getRawY();
                }
                if (canDrag) {
                    float offset = event.getRawY() - lastY;
                    if (getScaleY() - offset < 0) {
                        if (offset > 0) {
                            offset = offset / 3;
                        }
                    } else if (getScrollY() - offset > mLrcRows.size() * (mCurSizeForOtherLrc + mCurPadding) - mCurPadding) {
                        if (offset < 0) {
                            offset = offset / 3;
                        }
                    }
                    scrollBy(getScrollX(), -(int) offset);
                    lastY = event.getRawY();
                    int currentRow = (int) (getScrollY() / (mCurSizeForOtherLrc + mCurPadding));
                    currentRow = Math.min(currentRow, mLrcRows.size() - 1);
                    currentRow = Math.max(currentRow, 0);
                    seekTo(mLrcRows.get(currentRow).getStartTime(), false, false);
                    return true;

                }
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!canDrag) {
                    if (onLrcClickListener != null) {
                        onLrcClickListener.onClick();
                    }
                } else {
                    if (onSeekToListener != null && mCurRow != -1) {
                        onSeekToListener.onSeekTo(mLrcRows.get(mCurRow).getStartTime());
                    }
                    if (getScrollY() < 0) {
                        smoothScrollTo(0, DURATION_FOR_ACTION_UP);
                    } else if (getScrollY() > mLrcRows.size() * (mCurSizeForOtherLrc + mCurPadding) - mCurPadding) {
                        smoothScrollTo((int) (mLrcRows.size() * (mCurSizeForOtherLrc + mCurPadding) - mCurPadding), DURATION_FOR_ACTION_UP);
                    }

                    canDrag = false;
                    mIsDrawTimeLine = false;
                    invalidate();
                }
                break;
        }
        return true;
    }

    /**
     * 平滑的移动到某处
     *
     * @param dstY
     */
    private void smoothScrollTo(int dstY, int duration) {
        int oldScrollY = getScrollY();
        int offset = dstY - oldScrollY;
        mScroller.startScroll(getScrollX(), oldScrollY, getScrollX(), offset, duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished()) {
            if (mScroller.computeScrollOffset()) {
                int oldY = getScrollY();
                int y = mScroller.getCurrY();
                if (oldY != y && !canDrag) {
                    scrollTo(getScrollX(), y);
                }
                mCurFraction = mScroller.timePassed() * 3f / DURATION_FOR_LRC_SCROLL;
                mCurFraction = Math.min(mCurFraction, 1F);
                invalidate();
            }
        }
    }

    /**
     * 停止歌词的滚动
     */
    private void stopScrollLrc() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
        mCurTextXForHighLightLrc = 0;
    }

    private OnSeekToListener onSeekToListener;

    public void setOnSeekToListener(OnSeekToListener onSeekToListener) {
        this.onSeekToListener = onSeekToListener;
    }

    public interface OnSeekToListener {
        void onSeekTo(int progress);
    }

    private OnLrcClickListener onLrcClickListener;

    public void setOnLrcClickListener(OnLrcClickListener onLrcClickListener) {
        this.onLrcClickListener = onLrcClickListener;
    }

    public interface OnLrcClickListener {
        void onClick();
    }

    @Override
    public void seLyricRows(List<LyricRow> lrcRows) {

    }

    @Override
    public void seekTo(int progress, boolean fromSeekBar, boolean fromSeekBarByUser) {

    }

    @Override
    public void setLrcScalingFactor(float scalingFactor) {

    }

    @Override
    public void reset() {

    }
}
