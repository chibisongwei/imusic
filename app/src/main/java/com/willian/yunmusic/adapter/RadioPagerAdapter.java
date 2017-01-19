package com.willian.yunmusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * 电台类型
 */
public class RadioPagerAdapter extends PagerAdapter {

    private List<GridView> gridViews;

    public RadioPagerAdapter(List<GridView> gridViews) {
        this.gridViews = gridViews;
    }

    @Override
    public int getCount() {
        return gridViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(gridViews.get(position));
        return gridViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(gridViews.get(position));
    }
}
