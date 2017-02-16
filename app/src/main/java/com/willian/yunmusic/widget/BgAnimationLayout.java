package com.willian.yunmusic.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.willian.yunmusic.R;

/**
 * 自定义背景动画切换布局
 */

public class BgAnimationLayout extends RelativeLayout {

    private final static int INDEX_BACKGROUND = 0;
    private final static int INDEX_FOREGROUND = 1;
    private final static int DURATION_ANIMATION = 500;

    private LayerDrawable mLayerDrawable;

    private ObjectAnimator mObjectAnimator;

    public BgAnimationLayout(Context context) {
        this(context, null);
    }

    public BgAnimationLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BgAnimationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayerDrawable();
        initObjectAnimator();
    }

    private void initLayerDrawable() {
        Drawable backgroundDrawable = getContext().getDrawable(R.drawable.playpage_background);
        Drawable[] drawables = new Drawable[2];
        // 初始化时先将前景与背景颜色设为一致
        drawables[INDEX_BACKGROUND] = backgroundDrawable;
        drawables[INDEX_FOREGROUND] = backgroundDrawable;

        mLayerDrawable = new LayerDrawable(drawables);
    }

    private void initObjectAnimator() {
        mObjectAnimator = ObjectAnimator.ofFloat(this, "number", 0f, 1.0f);
        mObjectAnimator.setDuration(DURATION_ANIMATION);
        mObjectAnimator.setInterpolator(new AccelerateInterpolator());
        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int foregroundAlpha = (int) ((float) animation.getAnimatedValue() * 255);
                // 动态设置Drawable的透明度，让前景图逐渐显示
                mLayerDrawable.getDrawable(INDEX_FOREGROUND).setAlpha(foregroundAlpha);
                BgAnimationLayout.this.setBackground(mLayerDrawable);
            }
        });
        mObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束后，记得将原来的背景图及时更新
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mLayerDrawable.setDrawable(INDEX_BACKGROUND, mLayerDrawable.getDrawable(INDEX_FOREGROUND));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void setForeground(Drawable drawable){
        mLayerDrawable.setDrawable(INDEX_FOREGROUND, drawable);
    }

    public void beginAnimation(){
        mObjectAnimator.start();
    }
}
