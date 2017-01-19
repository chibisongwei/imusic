package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.CoverItemAdapter;
import com.willian.yunmusic.adapter.RadioItemAdapter;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.widget.WrapGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌单
 */
public class SubSheetFragment extends BaseFragment {

    private View mView;

    private ListView mListView;

    private RadioItemAdapter mRadioAdapter;

    private List<List<CoverItem>> mRadioList = new ArrayList<List<CoverItem>>();

    private View mHeaderView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sheet, container, false);

        initView();

        return mView;
    }

    private void initView() {
        mHeaderView = View.inflate(getActivity(), R.layout.fragment_sheet_header, null);

        List<CoverItem> radioCovers = new ArrayList<CoverItem>();
        radioCovers.add(new CoverItem(R.drawable.song_cover, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover2, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover3, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover4, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover5, "I need you now", ""));
        radioCovers.add(new CoverItem(R.drawable.song_cover6, "I need you now", ""));

        mRadioList.add(radioCovers);

        mListView = (ListView) mView.findViewById(R.id.lv_sheet);
        mRadioAdapter = new RadioItemAdapter(getActivity(), mRadioList);
        mListView.setAdapter(mRadioAdapter);
        mListView.addHeaderView(mHeaderView);
    }
}
