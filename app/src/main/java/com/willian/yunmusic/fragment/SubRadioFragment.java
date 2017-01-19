package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.CommItemAdapter;
import com.willian.yunmusic.adapter.CoverItemAdapter;
import com.willian.yunmusic.adapter.RadioAdapter;
import com.willian.yunmusic.adapter.RadioItemAdapter;
import com.willian.yunmusic.adapter.RadioPagerAdapter;
import com.willian.yunmusic.bean.CommItem;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.util.DisplayUtils;
import com.willian.yunmusic.util.LoggerUtil;
import com.willian.yunmusic.widget.WrapGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主播电台
 */
public class SubRadioFragment extends Fragment {

    private static final String TAG = "SubRadioFragment";

    private View mView;

    private ListView mListView;

    private ViewPager mViewPager;

    private ImageView mFirstDot;

    private ImageView mSecondDot;

    private ImageView mThirdDot;

    private List<List<CoverItem>> mRadioList = new ArrayList<List<CoverItem>>();

    private RadioItemAdapter mRadioAdapter;

    private View mHeaderView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_radio, container, false);

        initView();

        handleEvent();

        return mView;
    }

    private void initView() {
        // 初始化HeaderView
        initHeaderView();
        // 初始化滚动数据
        initRollView();
        // 初始化ListView
        initListView();
    }

    private void initListView() {
        List<CoverItem> radioCovers = new ArrayList<CoverItem>();
        radioCovers.add(new CoverItem(R.drawable.song_cover, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover2, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover3, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover4, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover5, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover6, "I need you now", ""));

        mRadioList.add(radioCovers);

        mListView = (ListView) mView.findViewById(R.id.lv_radio);
        mRadioAdapter = new RadioItemAdapter(getActivity(), mRadioList);
        mListView.setAdapter(mRadioAdapter);
        mListView.addHeaderView(mHeaderView);
    }

    private void initHeaderView() {

        mHeaderView = View.inflate(getActivity(), R.layout.fragment_radio_header, null);
        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.vp_roll_image);
        mFirstDot = (ImageView) mHeaderView.findViewById(R.id.iv_dot1);
        mSecondDot = (ImageView) mHeaderView.findViewById(R.id.iv_dot2);
        mThirdDot = (ImageView) mHeaderView.findViewById(R.id.iv_dot3);

        WrapGridView radioGridView = (WrapGridView) mHeaderView.findViewById(R.id.gv_radio);

        List<CoverItem> radioCovers = new ArrayList<CoverItem>();
        radioCovers.add(new CoverItem(R.drawable.song_cover, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover2, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover3, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover4, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover5, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover6, "I need you now", ""));


        CoverItemAdapter radioGridAdapter = new CoverItemAdapter(getActivity(), radioCovers);
        radioGridView.setAdapter(radioGridAdapter);
    }

    private void initRollView() {
        // 获取屏幕宽度
        int gvWidth = DisplayUtils.getScreenWidthPixels(getActivity());
        // 边距
        int spacing = DisplayUtils.dp2px(getActivity(), 5);
        // GridView中item的宽度
        int itemWidth = (gvWidth - spacing * 5) / 4;
        // GirdView的高度
        int gvHeight = itemWidth * 2 + spacing * 3;

        List<GridView> gridViews = new ArrayList<GridView>();
        List<CommItem> itemList = new ArrayList<CommItem>();

        for (int i = 0; i < 18; i++) {
            itemList.add(new CommItem(R.mipmap.lay_icn_manage, getActivity().getResources().getString(R.string.star_host)));

            if (itemList.size() == 8) {
                GridView gv = createEmotionGridView(itemList, gvWidth, spacing, itemWidth, gvHeight);
                gridViews.add(gv);
                // 添加完一组数据后,重新创建一个集合
                itemList = new ArrayList<CommItem>();
            }
        }

        // 检查最后是否有不足8个的剩余情况
        if (itemList.size() > 0) {
            GridView gv = createEmotionGridView(itemList, gvWidth, spacing, itemWidth, gvHeight);
            gridViews.add(gv);
        }

        // 将多个GridView添加显示到ViewPager中
        RadioPagerAdapter radioPagerAdapter = new RadioPagerAdapter(gridViews);
        mViewPager.setAdapter(radioPagerAdapter);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(gvWidth, gvHeight);
        mViewPager.setLayoutParams(params);

        LoggerUtil.showLog(TAG, "===============" + gridViews.size(), 6);
    }

    /**
     * 创建显示表情的GridView
     */
    private GridView createEmotionGridView(List<CommItem> itemList, int gvWidth, int padding, int itemWidth, int gvHeight) {
        // 创建GridView
        GridView gv = new GridView(getActivity());
        gv.setBackgroundResource(R.color.bg_gray);
        gv.setSelector(R.color.transparent);
        gv.setNumColumns(4);
        gv.setPadding(padding, padding, padding, padding);
        gv.setHorizontalSpacing(padding);
        gv.setVerticalSpacing(padding);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(gvWidth, gvHeight);
        gv.setLayoutParams(params);
        RadioAdapter adapter = new RadioAdapter(getActivity(), itemList, itemWidth);
        gv.setAdapter(adapter);
        return gv;
    }

    /**
     * 将小圆点都重置为灰色
     */
    private void resetImages() {
        mFirstDot.setImageResource(R.mipmap.grey_point);
        mSecondDot.setImageResource(R.mipmap.grey_point);
        mThirdDot.setImageResource(R.mipmap.grey_point);
    }

    private void handleEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImages();
                switch (position) {
                    case 0:
                        mFirstDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 1:
                        mSecondDot.setImageResource(R.mipmap.red_point);
                        break;
                    case 2:
                        mThirdDot.setImageResource(R.mipmap.red_point);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
