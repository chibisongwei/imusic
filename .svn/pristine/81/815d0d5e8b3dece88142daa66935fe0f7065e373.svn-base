package com.willian.yunmusic.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.constant.Constant;

/**
 * 基类Fragment
 */
public class BaseFragment extends Fragment {

    /**
     * 广播接收器
     */
    private BroadcastReceiver mStatusListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 歌曲
            MusicInfo musicInfo = intent.getParcelableExtra("musicInfo");
            switch (action) {
                case Constant.Action.UPDATE_MUSIC_INFO:
                    updateMusicInfo(musicInfo);
                    reloadAdapter();
                    break;
                case Constant.Action.PLAY_QUEUE_CHANGED:
                    reloadAdapter();
                    break;
                case Constant.Action.MUSIC_PREPARED:
                    updateMusicTime();
                    break;
                case Constant.Action.MUSIC_COUNT_CHANGED:
                    reloadAdapter();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.Action.UPDATE_MUSIC_INFO);
        // 动态注册广播
        getActivity().registerReceiver(mStatusListener, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 解注册
        getActivity().unregisterReceiver(mStatusListener);
    }

    public void updateMusicTime() {

    }

    public void updateMusicInfo(MusicInfo musicInfo) {

    }

    public void reloadAdapter() {

    }
}
