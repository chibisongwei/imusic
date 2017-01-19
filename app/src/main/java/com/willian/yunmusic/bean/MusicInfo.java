package com.willian.yunmusic.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 音乐实体类
 */

public class MusicInfo implements Parcelable {
    // ID
    public long musicId;
    // 标题
    public String musicTitle;
    // 艺术家
    public String musicArtist;
    // 专辑
    public String musicAlbum;
    // 时长
    public long musicDuration;
    // 大小
    public long musicSize;
    // 路径
    public String musicPath;

    public boolean isLocal;

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel in) {
            Bundle bundle = in.readBundle();
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.musicId = bundle.getLong("musicId");
            musicInfo.musicTitle = bundle.getString("musicTitle");
            musicInfo.musicArtist = bundle.getString("musicArtist");
            musicInfo.musicAlbum = bundle.getString("musicAlbum");
            musicInfo.musicDuration = bundle.getLong("musicDuration");
            musicInfo.musicSize = bundle.getLong("musicSize");
            musicInfo.musicPath = bundle.getString("musicPath");
            musicInfo.isLocal = bundle.getBoolean("isLocal");
            return musicInfo;
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putLong("musicId", musicId);
        bundle.putString("musicTitle", musicTitle);
        bundle.putString("musicArtist", musicArtist);
        bundle.putString("musicAlbum", musicAlbum);
        bundle.putLong("musicDuration", musicDuration);
        bundle.putLong("musicSize", musicSize);
        bundle.putString("musicPath", musicPath);
        bundle.putBoolean("isLocal", isLocal);
        dest.writeBundle(bundle);
    }

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public long getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(long musicDuration) {
        this.musicDuration = musicDuration;
    }

    public long getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(long musicSize) {
        this.musicSize = musicSize;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}
