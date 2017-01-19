package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willian.yunmusic.R;
import com.willian.yunmusic.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willian on 2016/7/25.
 */
public class DiscoverFragment extends Fragment {

    private static final String TAG = "DiscoverFragment";

    private View mView;

    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>(4);

    private List<String> mTitleList = new ArrayList<String>(4);

    private TitileAdapter mTitleAdapter;

    private SubRecomFragment mRecommendFragment;

    private SubSheetFragment mSheetFragment;

    private SubRadioFragment mRadioFragment;

    private SubRankFragment mRankFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_discover, container, false);

        initData();

        initView();

        LoggerUtil.showLog(TAG, "=======onCreateView========", 6);
        return mView;
    }

    private void initData() {

        mTitleList.add(getActivity().getResources().getString(R.string.recommend));
        mTitleList.add(getActivity().getResources().getString(R.string.song_sheet));
        mTitleList.add(getActivity().getResources().getString(R.string.radio_station));
        mTitleList.add(getActivity().getResources().getString(R.string.rank_list));

        if (mRecommendFragment == null) {
            mRecommendFragment = new SubRecomFragment();
            mFragmentList.add(mRecommendFragment);
        }
        if (mSheetFragment == null) {
            mSheetFragment = new SubSheetFragment();
            mFragmentList.add(mSheetFragment);
        }
        if (mRadioFragment == null) {
            mRadioFragment = new SubRadioFragment();
            mFragmentList.add(mRadioFragment);
        }
        if (mRankFragment == null) {
            mRankFragment = new SubRankFragment();
            mFragmentList.add(mRankFragment);
        }
    }

    private void initView() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.discover_tab_layout);
        mViewPager = (ViewPager) mView.findViewById(R.id.discover_view_pager);
        // 当Fragment中嵌套Fragment时，嵌套的Fragment要使用getChildFragmentManager()获取FragmentManager
        mTitleAdapter = new TitileAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTitleAdapter);
        // 设置ViewPager缓存4个视图，避免Fragment被重复创建
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class TitileAdapter extends FragmentPagerAdapter {

        public TitileAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}

