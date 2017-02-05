package com.willian.yunmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.willian.yunmusic.activity.BaseActivity;
import com.willian.yunmusic.constant.Constant;

import java.lang.ref.WeakReference;

/**
 * 播放状态变化的广播接收器
 */

public class PlaybackReceiver extends BroadcastReceiver {

    private WeakReference<BaseActivity> mActivityReference;

    public PlaybackReceiver(BaseActivity baseActivity) {
        mActivityReference = new WeakReference<>(baseActivity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        BaseActivity baseActivity = mActivityReference.get();
        if (baseActivity != null) {
            String action = intent.getAction();
            switch (action) {
                // 清空播放列表
                case Constant.Action.EMPTY_PLAY_QUEUE:

                    break;
                // 播放列表变化
                case Constant.Action.PLAY_QUEUE_CHANGED:
                    baseActivity.updatePlayQueue();
                    break;
                // 歌曲准备完毕
                case Constant.Action.MUSIC_PREPARED:
                    baseActivity.updateMusicTime();
                    break;
                // 更新歌曲信息
                case Constant.Action.UPDATE_MUSIC_INFO:
                    baseActivity.updateMusicInfo();
                    break;
                // 更新歌曲的缓冲进度
                case Constant.Action.UPDATE_BUFFER_PROGRESS:
                    baseActivity.updateBufferProgress();
                    break;
                default:
                    break;
            }
        }
    }
}
