package com.willian.yunmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.willian.yunmusic.constant.Constant;

/**
 * 播放音乐的广播接收器
 */

public class MusicPlayReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Constant.LOCAL_BROADCAST)) {
            int playState = intent.getIntExtra("PlayState", Constant.STATUS_NOFILE);
            switch (playState){
                case Constant.STATUS_PLAYING:
                    // 是否监听摇晃手机切换下一首歌曲
                    break;
                default:
                    break;
            }
        }
    }
}
