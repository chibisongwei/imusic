package com.willian.yunmusic.bean;

import java.util.List;

/**
 * 歌词信息
 */

public class LyricInfo {

    // 歌手
    private String author;
    // 标题
    private String title;
    // 专辑
    private String album;
    // 偏移量
    private long offset;
    // 每行歌词集合
    private List<LineInfo> lines;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public List<LineInfo> getLines() {
        return lines;
    }

    public void setLines(List<LineInfo> lines) {
        this.lines = lines;
    }
}
