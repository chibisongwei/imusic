package com.willian.yunmusic.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.AlbumFragmentAdapter;
import com.willian.yunmusic.fragment.AlbumFragment;
import com.willian.yunmusic.util.PlayUtil;
import com.willian.yunmusic.widget.AlbumViewPager;
import com.willian.yunmusic.widget.LyricView;

/**
 * 歌曲播放界面
 */

public class PlayingActivity extends BaseActivity {

    private static final String TAG = "PlayingActivity";

    private Toolbar mToolbar;
    // 专辑封面
    private LinearLayout mCoverLayout;

    private AlbumViewPager mViewPager;
    // 歌词信息
    private RelativeLayout mLyricLayout;

    private LyricView mLyricView;

    private AlbumFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        // 初始化
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        mCoverLayout = (LinearLayout) findViewById(R.id.ll_cover);

        mViewPager = (AlbumViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);

        PlaybarPagerTransformer transformer = new PlaybarPagerTransformer();
        mAdapter = new AlbumFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, transformer);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 初始化歌词界面信息
        initLrcView();
    }

    private void initLrcView() {
        mLyricLayout = (RelativeLayout) findViewById(R.id.rl_lyric);

        mLyricView = (LyricView) findViewById(R.id.music_lyric);

        mLyricView.setOnSeekToListener(new LyricView.OnSeekToListener() {
            @Override
            public void onSeekTo(int progress) {
                PlayUtil.seek(progress);
            }
        });
        // 点击歌词信息界面
        mLyricView.setOnLrcClickListener(new LyricView.OnLrcClickListener() {
            @Override
            public void onClick() {
                if (mLyricLayout.getVisibility() == View.VISIBLE) {
                    mLyricLayout.setVisibility(View.INVISIBLE);
                    mCoverLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewPager.setOnSingleTouchListener(new AlbumViewPager.OnSingleTouchListener() {
            @Override
            public void onSingleTouch(View v) {
                if (mCoverLayout.getVisibility() == View.VISIBLE) {
                    mCoverLayout.setVisibility(View.INVISIBLE);
                    mLyricLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private class PlaybarPagerTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

        }
    }
}
