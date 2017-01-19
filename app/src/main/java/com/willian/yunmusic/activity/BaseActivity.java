package com.willian.yunmusic.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.willian.yunmusic.BaseApplication;
import com.willian.yunmusic.R;
import com.willian.yunmusic.constant.Constant;
import com.willian.yunmusic.fragment.QuickControlFragment;
import com.willian.yunmusic.manager.ActivityManager;
import com.willian.yunmusic.receiver.PlaybackReceiver;

/**
 * Activity基类
 */

public class BaseActivity extends AppCompatActivity {

    protected BaseApplication mApplication;

    private ActivityManager activityManager = ActivityManager.getInstance();

    private QuickControlFragment mControlFragment;

    private PlaybackReceiver mPlaybackReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (BaseApplication) getApplication();
        activityManager.addActivity(this);

        mPlaybackReceiver = new PlaybackReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.Action.EMPTY_PLAY_QUEUE);
        intentFilter.addAction(Constant.Action.PLAY_QUEUE_CHANGED);
        intentFilter.addAction(Constant.Action.MUSIC_PREPARED);
        intentFilter.addAction(Constant.Action.UPDATE_MUSIC_INFO);
        intentFilter.addAction(Constant.Action.UPDATE_BUFFER_PROGRESS);
        intentFilter.addAction(Constant.Action.MUSIC_COUNT_CHANGED);
        // 动态注册广播接收器
        registerReceiver(mPlaybackReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManager.removeActivity(this);
        // 取消注册
        unregisterReceiver(mPlaybackReceiver);
    }

    /**
     * 显示或隐藏底部播放控制栏
     */
    protected void showQuickControl(boolean isShow) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isShow) {
            if (mControlFragment == null) {
                mControlFragment = QuickControlFragment.getInstance();
                fragmentTransaction.add(R.id.layout_bottom, mControlFragment);
            } else {
                fragmentTransaction.show(mControlFragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        } else {
            fragmentTransaction.hide(mControlFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * 更新歌曲信息
     */
    public void updateMusicInfo() {

    }

    /**
     * 更新播放列表
     */
    public void updatePlayQueue() {

    }

    /**
     * 更新歌曲播放时间
     */
    public void updateMusicTime() {

    }

    /**
     * 更新歌曲缓冲进度
     */
    public void updateBufferProgress() {

    }

    /**
     * 用户注销时销毁所有Activity
     */
    public void logout() {
        activityManager.finishAllActivity();
    }
}
