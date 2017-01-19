package com.willian.yunmusic.bean;

/**
 * 歌词行信息
 */

public class LineInfo {

    // 开始时间
    private long startTime;
    // 歌词内容
    private String content;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
