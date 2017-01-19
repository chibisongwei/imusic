package com.willian.yunmusic.manager;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.willian.yunmusic.R;

/**
 * TitleBar工具类
 */
public class TitleBarBuilder {

    private View mView;
    // 标题
    private TextView mTitleText;
    // 简介
    private TextView mCaptionText;
    // 右边Search Icon
    private ImageView mSearchIcon;
    // 右边More Icon
    private ImageView mMoreIcon;

    public TitleBarBuilder(View view) {
        mView = view.findViewById(R.id.layout_title_bar);

        mTitleText = (TextView) mView.findViewById(R.id.tv_title);
        mCaptionText = (TextView) mView.findViewById(R.id.tv_caption);
        mSearchIcon = (ImageView) mView.findViewById(R.id.icon_search);
        mMoreIcon = (ImageView) mView.findViewById(R.id.icon_more);
    }

    public TitleBarBuilder(Activity activity) {
        mView = activity.findViewById(R.id.layout_title_bar);

        mTitleText = (TextView) mView.findViewById(R.id.tv_title);
        mCaptionText = (TextView) mView.findViewById(R.id.tv_caption);
        mSearchIcon = (ImageView) mView.findViewById(R.id.icon_search);
        mMoreIcon = (ImageView) mView.findViewById(R.id.icon_more);
    }

    public TitleBarBuilder setTitleText(String text) {
        mTitleText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        mTitleText.setText(text);
        return this;
    }

    public TitleBarBuilder setCaptionText(String text) {
        mCaptionText.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        mCaptionText.setText(text);
        return this;
    }

    public TitleBarBuilder setSearchIcon(int resId) {
        mSearchIcon.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mSearchIcon.setImageResource(resId);
        return this;
    }

    public TitleBarBuilder setMoreIcon(int resId) {
        mMoreIcon.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mMoreIcon.setImageResource(resId);
        return this;
    }

    public View build() {
        return mView;
    }

}
