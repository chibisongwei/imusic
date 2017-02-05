package com.willian.yunmusic.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.willian.yunmusic.IPlayerService;
import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.service.PlayService;

import java.util.List;

/**
 * 播放工具类
 */

public class PlayUtil {

    private static final long[] sEmptyList;

    private static IPlayerService mPlayService;

    static {
        sEmptyList = new long[0];
    }

    /**
     * 绑定服务
     *
     * @param context
     */
    public static void bindService(Context context) {
        Intent intent = new Intent(context, PlayService.class);
        MyServiceConnection serviceConnection = new MyServiceConnection();
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 播放歌曲
     *
     * @param musicList
     * @param position
     */
    public static void play(List<MusicInfo> musicList, int position) {
        try {
            if (mPlayService != null) {
                // 添加歌曲到播放列表
                mPlayService.addToPlayList(musicList);
                // 播放点击的歌曲
                mPlayService.play(position);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放下一首
     */
    public static void playNext() {
        try {
            if (mPlayService != null) {
                mPlayService.next();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止或播放
     */
    public static void playOrPause() {
        try {
            if (mPlayService != null) {
                mPlayService.playOrPause();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否正在播放
     *
     * @return
     */
    public static final boolean isPlaying() {
        if (mPlayService != null) {
            try {
                return mPlayService.isPlaying();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取歌曲播放时长
     *
     * @return
     */
    public static long getDuration() {
        if (mPlayService != null) {
            try {
                return mPlayService.getDuration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 获取当前播放位置
     *
     * @return
     */
    public static long getPosition() {
        if (mPlayService != null) {
            try {
                return mPlayService.getDuration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void seek(final long position) {
        if (mPlayService != null) {
            try {
                mPlayService.seek(position);
            } catch (final RemoteException ignored) {
            }
        }
    }

    public static final long[] getQueue() {
        try {
            if (mPlayService != null) {
                return mPlayService.getQueue();
            } else {
            }
        } catch (final RemoteException ignored) {
        }
        return sEmptyList;
    }

    public static final String[] getAlbumPathAll() {
        if (mPlayService != null) {
            try {
                return mPlayService.getAlbumPathAll();
            } catch (final RemoteException ignored) {
            }
        }
        return null;
    }

    public static void initPlaybackServiceWithSettings() {
        try {
            if (mPlayService != null) {
                mPlayService.setLockscreenAlbum(true);
            }
        } catch (final RemoteException ignored) {

        }
    }

    private static class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayService = IPlayerService.Stub.asInterface(service);
            // 显示锁屏界面的歌曲封面信息
            initPlaybackServiceWithSettings();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPlayService = null;
        }
    }
}
