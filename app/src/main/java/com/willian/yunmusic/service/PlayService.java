package com.willian.yunmusic.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;

import com.willian.yunmusic.IPlayerService;
import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.constant.Constant;
import com.willian.yunmusic.manager.MusicPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 播放服务
 */

public class PlayService extends Service implements AudioManager.OnAudioFocusChangeListener {

    private MusicPlayer mMusicPlayer;

    private IBinder mIBinder = new ServerStub(this);

    private List<MusicInfo> mPlayList = new ArrayList<>();
    // 是否在锁屏界面显示专辑封面
    private boolean mShowAlbumOnLockscreen;

    private HashMap<Long, MusicInfo> mPlaylistInfo = new HashMap<>();
    // 播放位置索引
    private int mPlayPos = -1;

    private RequestLrc mRequestLrc;

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicPlayer = MusicPlayer.getInstance(this);
        // 动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.Action.GET_MUSIC_INFO);
        registerReceiver(mIntentReceiver, intentFilter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMusicPlayer.release();

        unregisterReceiver(mIntentReceiver);
    }

    /**
     * AIDL
     */
    private class ServerStub extends IPlayerService.Stub {

        private WeakReference<PlayService> mService;

        private ServerStub(PlayService service) {
            mService = new WeakReference<>(service);
        }

        @Override
        public void addToPlayList(List<MusicInfo> list) throws RemoteException {
            setPlayList(list);
        }

        @Override
        public void play(int position) throws RemoteException {
            mService.get().playMusic(position);
        }

        @Override
        public void pause() throws RemoteException {
            mService.get().pauseMusic();
        }

        @Override
        public void prev() throws RemoteException {
            mService.get().prevMusic();
        }

        @Override
        public void next() throws RemoteException {
            mService.get().nextMusic();
        }

        @Override
        public void playOrPause() throws RemoteException {
            mService.get().playOrPause();
        }

        @Override
        public long getDuration() throws RemoteException {
            return mService.get().getDuration();
        }

        @Override
        public long getPosition() throws RemoteException {
            return mService.get().getPosition();
        }

        @Override
        public boolean isPlaying() throws RemoteException {
            return mService.get().isPlaying();
        }

        @Override
        public void setLockscreenAlbum(boolean enabled) throws RemoteException {
            mService.get().setLockscreenAlbum(enabled);
        }

        @Override
        public long seek(long pos) throws RemoteException {
            return mService.get().seek(pos);
        }

        @Override
        public long[] getQueue() throws RemoteException {
            return mService.get().getQueue();
        }

        @Override
        public String[] getAlbumPathAll() throws RemoteException {
            return mService.get().getAlbumPathAll();
        }
    }

    /**
     * 添加歌曲到播放列表
     *
     * @param newList
     */
    public void setPlayList(List<MusicInfo> newList) {
        // 是否是新的歌曲列表
        boolean isNewList = true;
        if (mPlayList.size() == newList.size()) {
            isNewList = false;
            for (int i = 0; i < newList.size(); i++) {
                long musicId = newList.get(i).musicId;
                if (musicId != mPlayList.get(i).musicId) {
                    isNewList = true;
                    break;
                }
            }
        }
        if (isNewList) {
            mPlayList = newList;
            mMusicPlayer.setPlayList(newList);
        }
    }

    public void playMusic(int position) {
        mMusicPlayer.play(position);
    }

    public void playOrPause() {
        mMusicPlayer.playOrPause();
    }

    public void pauseMusic() {
        mMusicPlayer.pause();
    }

    public void prevMusic() {
        mMusicPlayer.prev();
    }

    public void nextMusic() {
        mMusicPlayer.next();
    }

    public boolean isPlaying() {
        return mMusicPlayer.isPlaying();
    }

    public long getDuration() {
        return mMusicPlayer.getDuration();
    }

    public long getPosition() {
        return mMusicPlayer.getPosition();
    }

    public long seek(long position) {
        if (position < 0) {
            position = 0;
        } else if (position > mMusicPlayer.getDuration()) {
            position = mMusicPlayer.getDuration();
        }
        long result = mMusicPlayer.seek(position);
        return result;
    }

    public long[] getQueue() {
        synchronized (this) {
            final int len = mPlayList.size();
            final long[] list = new long[len];
            for (int i = 0; i < len; i++) {
                list[i] = mPlayList.get(i).musicId;
            }
            return list;
        }
    }

    public String[] getAlbumPathAll() {
        synchronized (this) {
            try {
                int len = mPlaylistInfo.size();
                String[] albums = new String[len];
                long[] queue = getQueue();
                for (int i = 0; i < len; i++) {
                    albums[i] = mPlaylistInfo.get(queue[i]).musicAlbum;
                }
                return albums;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String[]{};
        }
    }

    public String getMusicTitle(int position) {
        MusicInfo musicInfo = mPlayList.get(position);
        String musicTitle = musicInfo.getMusicTitle();
        return musicTitle;
    }

    public void setLockscreenAlbum(boolean enabled) {
        mShowAlbumOnLockscreen = enabled;
        notifyChange(Constant.Action.META_CHANGED);
    }

    private void notifyChange(final String what) {
        if (what.equals(Constant.Action.META_CHANGED)) {
            //
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
                // 暂停播放，并释放Media资源
                if (mMusicPlayer.isPlaying()) {
                    mMusicPlayer.pause();
                    mMusicPlayer.release();
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // 暂停播放，但不释放Media资源
                if (mMusicPlayer.isPlaying()) {
                    mMusicPlayer.pause();
                }
                break;
        }
    }

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handleIntent(intent);
        }
    };

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case Constant.Action.GET_MUSIC_INFO:
                // 获取歌词和专辑封面
                getLrc(mPlayList.get(mPlayPos).musicId);
                break;
        }
    }

    /**
     * 获取并保存歌词
     */
    private void getLrc(long id) {
        String lrcPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.LRC_PATH;
        File file = new File(lrcPath + id);
        if (!file.exists()) {
            if(mRequestLrc != null){
            }
        }

    }

    /**
     * 将歌词写入文件
     *
     * @param file
     * @param lrc
     */
    private synchronized void saveLrcToFile(File file, String lrc) {
        try {
            FileOutputStream os = new FileOutputStream(file);
            os.write(lrc.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class RequestLrc implements Runnable{

        @Override
        public void run() {

        }
    }
}
