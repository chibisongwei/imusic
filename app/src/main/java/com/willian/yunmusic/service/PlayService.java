package com.willian.yunmusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.RemoteException;

import com.willian.yunmusic.IPlayerService;
import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.constant.Constant;
import com.willian.yunmusic.manager.MusicPlayer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicPlayer = MusicPlayer.getInstance(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMusicPlayer.release();
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

    public String getMusicTitle(int position) {
        MusicInfo musicInfo = mPlayList.get(position);
        String musicTitle = musicInfo.getMusicTitle();
        return musicTitle;
    }

    public void setLockscreenAlbum(boolean enabled) {
        mShowAlbumOnLockscreen = enabled;
        notifyChange(Constant.Action.META_CHANGED);
    }

    private void notifyChange(final String what){
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
}
