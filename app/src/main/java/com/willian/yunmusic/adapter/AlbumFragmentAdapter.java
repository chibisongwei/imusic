package com.willian.yunmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.willian.yunmusic.fragment.RoundFragment;
import com.willian.yunmusic.util.PlayUtil;

/**
 * 专辑封面适配器
 */
public class AlbumFragmentAdapter extends FragmentStatePagerAdapter {

    private int mChildCount = 0;

    public AlbumFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == PlayUtil.getQueue().length + 1 || position == 0) {
            return RoundFragment.newInstance("");
        }
        return RoundFragment.newInstance(PlayUtil.getAlbumPathAll()[position - 1]);
    }

    @Override
    public int getCount() {
        //左右各加一个
        return PlayUtil.getQueue().length + 2;
    }


    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
