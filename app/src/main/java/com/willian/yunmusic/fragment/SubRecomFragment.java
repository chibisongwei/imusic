package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.RecomCategoryAdapter;
import com.willian.yunmusic.adapter.RollPictureAdapter;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.bean.RecomCategory;
import com.willian.yunmusic.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 个性推荐
 */
public class SubRecomFragment extends BaseFragment {

    private static final String TAG = "SubRecomFragment";

    private ListView mListView;

    private RecomCategoryAdapter mCategoryAdapter;

    private List<RecomCategory> mCategoryList = new ArrayList<RecomCategory>();

    private View mView;

    private List<ImageView> mImageList = new ArrayList<ImageView>();

    private ViewPager mViewPager;

    private RollPictureAdapter mRollPictureAdapter;

    private ImageView mFirstDot;

    private ImageView mSecondDot;

    private ImageView mThirdDot;

    private ImageView mForthDot;

    private ImageView mFifthDot;

    private ImageView mSixthDot;

    private int curItem = 0;

    private ScheduledExecutorService executorService;
    // 是否开启自动轮播
    private boolean isAutoPlay = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(curItem);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommend, container, false);

        initData();

        initView();

        setData();

        return mView;
    }

    private void initData() {
        int images[] = new int[]{R.drawable.index_daily_ban1, R.drawable.index_daily_ban2,
                R.drawable.index_new_america, R.drawable.index_new_china,
                R.drawable.index_new_japan, R.drawable.index_new_korea};

        for (int resId : images) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resId);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageList.add(imageView);
        }
    }

    private void setData() {
        List<CoverItem> recomCovers = new ArrayList<CoverItem>();
        recomCovers.add(new CoverItem(R.drawable.song_cover, "I need you now", ""));
        recomCovers.add(new CoverItem(R.drawable.song_cover2, "I need you now", ""));
        recomCovers.add(new CoverItem(R.drawable.song_cover3, "I need you now", ""));
        recomCovers.add(new CoverItem(R.drawable.song_cover4, "I need you now", ""));
        recomCovers.add(new CoverItem(R.drawable.song_cover5, "I need you now", ""));
        recomCovers.add(new CoverItem(R.drawable.song_cover6, "I need you now", ""));

        List<CoverItem> radioCovers = new ArrayList<CoverItem>();
        radioCovers.add(new CoverItem(R.drawable.song_cover7, "I like you", "Jacky"));
        radioCovers.add(new CoverItem(R.drawable.song_cover6, "I like you", "Jacky"));
        radioCovers.add(new CoverItem(R.drawable.song_cover5, "I like you", "Jacky"));
        radioCovers.add(new CoverItem(R.drawable.song_cover4, "I like you", "Jacky"));
        radioCovers.add(new CoverItem(R.drawable.song_cover3, "I like you", "Jacky"));
        radioCovers.add(new CoverItem(R.drawable.song_cover2, "I like you", "Jacky"));

        mCategoryList.add(new RecomCategory(R.mipmap.recommend_icn_recmd, getActivity().getResources().getString(R.string.recommend_song_list), recomCovers));
        mCategoryList.add(new RecomCategory(R.mipmap.recommend_icn_radio, getActivity().getResources().getString(R.string.radio_station), radioCovers));

        mCategoryAdapter.notifyDataSetChanged();
    }

    private void initView() {

        View headerView = View.inflate(getActivity(), R.layout.fragment_recom_header, null);
        mListView = (ListView) mView.findViewById(R.id.lv_recom_list);
        mCategoryAdapter = new RecomCategoryAdapter(getActivity(), mCategoryList);
        mListView.setAdapter(mCategoryAdapter);
        mListView.addHeaderView(headerView);

        mFirstDot = (ImageView) headerView.findViewById(R.id.iv_dot1);
        mSecondDot = (ImageView) headerView.findViewById(R.id.iv_dot2);
        mThirdDot = (ImageView) headerView.findViewById(R.id.iv_dot3);
        mForthDot = (ImageView) headerView.findViewById(R.id.iv_dot4);
        mFifthDot = (ImageView) headerView.findViewById(R.id.iv_dot5);
        mSixthDot = (ImageView) headerView.findViewById(R.id.iv_dot6);

        mViewPager = (ViewPager) headerView.findViewById(R.id.vp_roll_image);
        mRollPictureAdapter = new RollPictureAdapter(mImageList);
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(mRollPictureAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curItem = position;
                resetImages();
                switch (curItem) {
                    case 0:
                        mFirstDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 1:
                        mSecondDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 2:
                        mThirdDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 3:
                        mForthDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 4:
                        mFifthDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 5:
                        mSixthDot.setImageResource(R.mipmap.red_point);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    // 滑动结束，即切换完毕或者加载完毕
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (!isAutoPlay && mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                            mViewPager.setCurrentItem(0);
                        } else if (!isAutoPlay && mViewPager.getCurrentItem() == 0) {
                            mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
                        }
                        break;
                    // 手势滑动空闲中
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isAutoPlay = false;
                        break;
                    // 界面切换中
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isAutoPlay = true;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 将小圆点都重置为灰色
     */
    private void resetImages() {
        mFirstDot.setImageResource(R.mipmap.grey_point);
        mSecondDot.setImageResource(R.mipmap.grey_point);
        mThirdDot.setImageResource(R.mipmap.grey_point);
        mForthDot.setImageResource(R.mipmap.grey_point);
        mFifthDot.setImageResource(R.mipmap.grey_point);
        mSixthDot.setImageResource(R.mipmap.grey_point);
    }

    /**
     * 开始滚动
     */
    private void startRoll() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        // 实现自动轮播，每5秒切换一次图片
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                synchronized (mViewPager) {
                    curItem = (curItem + 1) % mImageList.size();
                    mHandler.obtainMessage().sendToTarget();
                }
            }
        }, 1, 5, TimeUnit.SECONDS);
    }

    /**
     * 停止滚动
     */
    private void stopRoll() {
        executorService.shutdown();
    }

    @Override
    public void onStart() {
        super.onStart();
        LoggerUtil.showLog(TAG, "=====onStart=====", 6);
        if (isAutoPlay) {
            startRoll();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LoggerUtil.showLog(TAG, "=====onStop=====", 6);
        stopRoll();
    }
}
