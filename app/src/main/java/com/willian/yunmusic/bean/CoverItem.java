package com.willian.yunmusic.bean;

/**
 * 专辑封面信息
 */
public class CoverItem {

    private int coverImage;

    private String coverName;

    private String coverSinger;

    public CoverItem(int coverImage, String coverName, String coverSinger) {
        this.coverImage = coverImage;
        this.coverName = coverName;
        this.coverSinger = coverSinger;
    }

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public String getCoverSinger() {
        return coverSinger;
    }

    public void setCoverSinger(String coverSinger) {
        this.coverSinger = coverSinger;
    }
}
