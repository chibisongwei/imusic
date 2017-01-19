package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.CommItemAdapter;
import com.willian.yunmusic.adapter.OfficialTopAdapter;
import com.willian.yunmusic.bean.CommItem;
import com.willian.yunmusic.widget.WrapHeightListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 排行榜
 */
public class SubRankFragment extends Fragment {

    private View mView;

    private ScrollView mScrollView;

    private WrapHeightListView mOfficialListView;

    private WrapHeightListView mGlobalListView;

    private WrapHeightListView mUserListView;

    private OfficialTopAdapter mOfficialAdapter;

    private CommItemAdapter mGlobalAdapter;

    private CommItemAdapter mUserAdapter;

    private List<CommItem> mOfficialList = new ArrayList<CommItem>();

    private List<CommItem> mGlobalList = new ArrayList<CommItem>();

    private List<CommItem> mUserList = new ArrayList<CommItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_rank, container, false);

        initView();

        setData();
        /**
         * 解决嵌套ListVew的ScrollView的界面布局默认起始位置不是最顶部的问题
         */
        mOfficialListView.setFocusable(false);
        mGlobalListView.setFocusable(false);
        mUserListView.setFocusable(false);

        return mView;
    }

    private void setData() {
        mOfficialList.add(new CommItem(R.drawable.ranklist_first, "1.刚刚好-薛之谦", "2.遗憾-孙露", "3.Lost in the Stars-张杰"));
        mOfficialList.add(new CommItem(R.drawable.ranklist_first, "1.刚刚好-薛之谦", "2.遗憾-孙露", "3.Lost in the Stars-张杰"));
        mOfficialList.add(new CommItem(R.drawable.ranklist_first, "1.刚刚好-薛之谦", "2.遗憾-孙露", "3.Lost in the Stars-张杰"));
        mOfficialList.add(new CommItem(R.drawable.ranklist_first, "1.刚刚好-薛之谦", "2.遗憾-孙露", "3.Lost in the Stars-张杰"));
        mOfficialList.add(new CommItem(R.drawable.ranklist_first, "1.刚刚好-薛之谦", "2.遗憾-孙露", "3.Lost in the Stars-张杰"));

        mGlobalList.add(new CommItem(R.drawable.rank_global1, "云音乐电音榜", "每周五更新", ""));
        mGlobalList.add(new CommItem(R.drawable.rank_global2, "云音乐ACG音乐榜", "每周四更新", ""));
        mGlobalList.add(new CommItem(R.drawable.rank_global3, "韩国Melon排行榜周榜", "每周一更新", ""));
        mGlobalList.add(new CommItem(R.drawable.rank_global4, "韩国Melon原声榜", "每周一更新", ""));
        mGlobalList.add(new CommItem(R.drawable.rank_global5, "韩国Mnet排行榜周榜", "每周一更新", ""));

        mUserList.add(new CommItem(R.drawable.rank_global1, "音乐达人榜", "每周一更新", ""));
        mUserList.add(new CommItem(R.drawable.rank_global2, "音乐新人榜", "每周一更新", ""));

        mOfficialAdapter.notifyDataSetChanged();
        mGlobalAdapter.notifyDataSetChanged();
        mUserAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mScrollView = (ScrollView) mView.findViewById(R.id.sv_rank);
        mOfficialListView = (WrapHeightListView) mView.findViewById(R.id.lv_official_top);
        mGlobalListView = (WrapHeightListView) mView.findViewById(R.id.lv_global_top);
        mUserListView = (WrapHeightListView) mView.findViewById(R.id.lv_user_top);

        mOfficialAdapter = new OfficialTopAdapter(getActivity(), mOfficialList);
        mGlobalAdapter = new CommItemAdapter(getActivity(), mGlobalList);
        mUserAdapter = new CommItemAdapter(getActivity(), mUserList);

        mOfficialListView.setAdapter(mOfficialAdapter);
        mGlobalListView.setAdapter(mGlobalAdapter);
        mUserListView.setAdapter(mUserAdapter);
    }
}
