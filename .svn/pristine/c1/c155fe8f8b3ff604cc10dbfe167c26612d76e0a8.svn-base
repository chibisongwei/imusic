package com.willian.yunmusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 图片滚动适配器
 */
public class RollPictureAdapter extends PagerAdapter {

    private List<ImageView> mImageList;

    public RollPictureAdapter(List<ImageView> imageList) {
        this.mImageList = imageList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mImageList.get(position));
        return mImageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mImageList.get(position));
    }
}
