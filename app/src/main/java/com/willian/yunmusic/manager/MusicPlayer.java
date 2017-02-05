package com.willian.yunmusic.manager;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.util.SharedPreferencesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 音频播放器
 */

public class MusicPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    // 循环播放
    public static final int LIST_LOOP_PLAY = 1;
    // 顺序播放
    public static final int LIST_ORDER_PLAY = 2;
    // 随机播放
    public static final int LIST_RANDOM_PLAY = 3;
    // 单曲循环
    public static final int SINGLE_LOOP_PLAY = 4;

    private Context mContext;

    private MediaPlayer mMediaPlayer;

    private static MusicPlayer mInstance;
    // 是否暂停
    private boolean isPause;
    // 正在播放的歌曲序号
    private int mPlayingPos;
    // 播放列表
    private List<MusicInfo> mPlayList = new ArrayList<MusicInfo>();

    private MusicPlayer(Context context) {
        this.mContext = context;
    }

    public static MusicPlayer getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MusicPlayer(context);
        }
        return mInstance;
    }

    public void setPlayList(List<MusicInfo> musicList) {
        this.mPlayList = musicList;
    }

    /**
     * 开始播放
     */
    public void play(int position) {

        if (mPlayList.size() == 0) {
            return;
        }
        // 当前播放歌曲的序号
        mPlayingPos = getPosition(position);
        // 当前播放的音乐
        MusicInfo musicInfo = mPlayList.get(mPlayingPos);
        // 播放的歌曲路径
        String musicPath = musicInfo.getMusicPath();

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(this);
        } else {
            mMediaPlayer.reset();
        }

        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        try {
            mMediaPlayer.setDataSource(musicPath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 播放或暂停
     */
    public void playOrPause() {
        if (isPlaying()) {
            pause();
        } else if (isPause()) {
            resume();
        } else {
            play(mPlayingPos);
        }
    }

    /**
     * 重新播放
     */
    public void resume() {
        if (isPause()) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 释放
     */
    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    /**
     * 上一首
     */
    public void prev() {
        mPlayingPos--;
        play(mPlayingPos);
    }

    /**
     * 下一首
     */
    public void next() {
        mPlayingPos++;
        play(mPlayingPos);
    }

    /**
     * 是否暂停
     *
     * @return
     */
    public boolean isPause() {
        return mMediaPlayer != null && isPause;
    }

    /**
     * 是否在播放
     *
     * @return
     */
    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    /**
     * 获取时长
     *
     * @return
     */
    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return -1;
    }

    public long getPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return -1;
    }

    /**
     * 指定播放位置
     *
     * @param whereto
     * @return
     */
    public long seek(final long whereto) {
        mMediaPlayer.seekTo((int) whereto);
        return whereto;
    }

    /**
     * 获取歌曲序号
     *
     * @param pos
     * @return
     */
    private int getPosition(int pos) {
        if (pos < 0) {
            pos = mPlayList.size() - 1;
        } else if (pos >= mPlayList.size()) {
            pos = 0;
        }
        return pos;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // 当前歌曲播放完后，根据播放模式判断下一首
        int playMode = SharedPreferencesUtil.getPlayMode(mContext);
        switch (playMode) {
            case LIST_LOOP_PLAY:
                mPlayingPos++;
                play(mPlayingPos);
                break;
            case LIST_ORDER_PLAY:
                mPlayingPos++;
                play(mPlayingPos);
                break;
            case LIST_RANDOM_PLAY:
                mPlayingPos = new Random().nextInt(mPlayList.size());
                play(mPlayingPos);
                break;
            case SINGLE_LOOP_PLAY:
                play(mPlayingPos);
                break;
            default:
                mPlayingPos++;
                play(mPlayingPos);
                break;
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
