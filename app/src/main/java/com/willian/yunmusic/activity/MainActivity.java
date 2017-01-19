package com.willian.yunmusic.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.FragmentAdapter;
import com.willian.yunmusic.fragment.DiscoverFragment;
import com.willian.yunmusic.fragment.FriendFragment;
import com.willian.yunmusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    private boolean isOpen;

    private Toolbar mToolbar;

    private ImageView mMenuIcon;

    private ImageView mDiscoverIcon;

    private ImageView mMusicIcon;

    private ImageView mFriendIcon;

    private ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>(3);

    private FragmentAdapter mFragmentAdapter;

    private NavigationView mMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        initView();

        handleEvent();
    }

    private void handleEvent() {
        mMenuIcon.setOnClickListener(this);
        mDiscoverIcon.setOnClickListener(this);
        mMusicIcon.setOnClickListener(this);
        mFriendIcon.setOnClickListener(this);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImage();
                switch (position) {
                    case 0:
                        mDiscoverIcon.setSelected(true);
                        break;
                    case 1:
                        mMusicIcon.setSelected(true);
                        break;
                    case 2:
                        mFriendIcon.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 监听Menu Item点击事件
        mMenuLayout.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
       switch (item.getItemId()){
           case R.id.item_msg:
               break;
           default:
               break;
       }
    }

    private void initFragment() {
        mFragmentList.add(new DiscoverFragment());
        mFragmentList.add(new MusicFragment());
        mFragmentList.add(new FriendFragment());
    }

    private void initView() {
        mMenuLayout = (NavigationView) findViewById(R.id.layout_menu);
        mMenuIcon = (ImageView) findViewById(R.id.iv_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mDiscoverIcon = (ImageView) findViewById(R.id.bar_disco);
        mMusicIcon = (ImageView) findViewById(R.id.bar_music);
        mFriendIcon = (ImageView) findViewById(R.id.bar_friends);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        // ViewPager默认只会缓存一个页面，如果Fragment超过2个，设置多缓存一个Fragment
        mViewPager.setOffscreenPageLimit(2);
        // 初始化显示MusicFragment
        mMusicIcon.setSelected(true);
        mViewPager.setCurrentItem(1);
    }

    /**
     * 点击Back健不销毁当前Activity，与Home健效果一样
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOpen) {
                mDrawerLayout.closeDrawer(mMenuLayout);
                isOpen = false;
                return false;
            } else {
                // true对任何Activity都适用
                // false只对栈底Activity有效
                moveTaskToBack(false);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                if (!isOpen) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    isOpen = true;
                }
                break;
            case R.id.bar_disco:
                mDiscoverIcon.setSelected(true);
                mMusicIcon.setSelected(false);
                mFriendIcon.setSelected(false);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.bar_music:
                mDiscoverIcon.setSelected(false);
                mMusicIcon.setSelected(true);
                mFriendIcon.setSelected(false);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.bar_friends:
                mDiscoverIcon.setSelected(false);
                mMusicIcon.setSelected(false);
                mFriendIcon.setSelected(true);
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    /**
     * 重置所有Icon的选中状态
     */
    private void resetImage() {
        mDiscoverIcon.setSelected(false);
        mMusicIcon.setSelected(false);
        mFriendIcon.setSelected(false);
    }
}
