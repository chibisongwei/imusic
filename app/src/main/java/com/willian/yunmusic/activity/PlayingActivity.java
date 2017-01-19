package com.willian.yunmusic.activity;

import android.os.Bundle;

import com.willian.yunmusic.R;

/**
 * 歌曲播放界面
 */

public class PlayingActivity extends BaseActivity {

    private static final String TAG = "PlayingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        // 初始化
        initView();
    }

    private void initView() {

    }
}
