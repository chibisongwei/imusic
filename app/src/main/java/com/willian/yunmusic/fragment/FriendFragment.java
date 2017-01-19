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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willian on 2016/7/25.
 */
public class FriendFragment extends Fragment {

    private View mView;

    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>(3);

    private List<String> mTitleList = new ArrayList<String>(3);

    private TitileAdapter mTitleAdapter;

    private SubDynamicFragment mDynamicFragment;

    private SubNearbyFragment mNearbyFragment;

    private SubFellowFragment mFellowFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_friend, container, false);

        initData();

        initView();

        return mView;
    }

    private void initData() {

        mTitleList.add(getActivity().getResources().getString(R.string.dynamic));
        mTitleList.add(getActivity().getResources().getString(R.string.nearby));
        mTitleList.add(getActivity().getResources().getString(R.string.fellow));

        if (mDynamicFragment == null) {
            mDynamicFragment = new SubDynamicFragment();
            mFragmentList.add(mDynamicFragment);
        }
        if (mNearbyFragment == null) {
            mNearbyFragment = new SubNearbyFragment();
            mFragmentList.add(mNearbyFragment);
        }
        if (mFellowFragment == null) {
            mFellowFragment = new SubFellowFragment();
            mFragmentList.add(mFellowFragment);
        }
    }

    private void initView() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.friend_tab_layout);
        mViewPager = (ViewPager) mView.findViewById(R.id.friend_view_pager);
        // 当Fragment中嵌套Fragment时，嵌套的Fragment要使用getChildFragmentManager()获取FragmentManager
        mTitleAdapter = new TitileAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTitleAdapter);
        // 设置ViewPager缓存3个视图，避免Fragment被重复创建
        mViewPager.setOffscreenPageLimit(3);
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

