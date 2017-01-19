package com.willian.yunmusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willian.yunmusic.R;
import com.willian.yunmusic.adapter.CommItemAdapter;
import com.willian.yunmusic.bean.CommItem;
import com.willian.yunmusic.widget.WrapHeightListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 好友界面
 */
public class SubFellowFragment extends BaseFragment {

    private View mView;

    private WrapHeightListView mFriendList;

    private CommItemAdapter mFriendAdapter;

    private List<CommItem> mItemList = new ArrayList<CommItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fellow, container, false);

        initView();

        setData();

        return mView;
    }

    private void initView() {
        mFriendList = (WrapHeightListView) mView.findViewById(R.id.lv_friends);
        mFriendAdapter = new CommItemAdapter(getActivity(), mItemList);
        mFriendList.setAdapter(mFriendAdapter);
    }

    private void setData() {
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "张小凡", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "林惊羽", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "曾书书", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "陆雪琪", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "碧瑶", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "碧瑶", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "碧瑶", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "碧瑶", R.mipmap.list_icn_more));
        mItemList.add(new CommItem(R.mipmap.msg_icn_user, "碧瑶", R.mipmap.list_icn_more));
        mFriendAdapter.notifyDataSetChanged();
    }
}
