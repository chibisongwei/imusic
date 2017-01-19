// IPlayerService.aidl
package com.willian.yunmusic;

import com.willian.yunmusic.bean.MusicInfo;

interface IPlayerService {

    void addToPlayList(in List<MusicInfo> list);

    void play(int position);

    void pause();

    void prev();

    void next();

    void playOrPause();

    long getDuration();

    long getPosition();

    boolean isPlaying();

    void setLockscreenAlbum(boolean enabled);
}
