package com.willian.yunmusic.bean;

import android.text.TextUtils;

import com.willian.yunmusic.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌词实体类
 */

public class LyricRow implements Comparable<LyricRow> {

    private static final String TAG = "LyricRow";

    /**
     * 开始时间 为00:10:00
     */
    private String startTimeStr;

    /**
     * 开始时间 毫米数  00:10:00  为10000
     */
    private int startTime;

    /**
     * 歌词内容
     */
    private String content;

    /**
     * 该行歌词显示的总时间
     */
    private int totalTime;

    public LyricRow(String timeStr, int time, String content) {
        this.startTimeStr = timeStr;
        this.startTime = time;
        this.content = content;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public int compareTo(LyricRow another) {
        return (this.startTime - another.startTime);
    }

    /**
     * 将歌词文件中的某一行解析成一个List<LyricRow>
     * 因为一行中可能包含了多个LyricRow对象
     * 比如[03:33.02][00:36.37]当鸽子不再象征和平，就包含了2个对象
     *
     * @param lyricLine
     * @return
     */
    public static final List<LyricRow> createLyricRow(String lyricLine) {
        if (!lyricLine.startsWith("[") || lyricLine.indexOf("]") != 9) {
            return null;
        }
        // 最后一个"]"
        int lastIndexOfRightBracket = lyricLine.lastIndexOf("]");
        // 歌词内容
        String content = lyricLine.substring(lastIndexOfRightBracket + 1, lyricLine.length());
        // 截取出歌词时间，并将"[" 和"]" 替换为"-"   [offset:0]
        // -03:33.02--00:36.37-
        String times = lyricLine.substring(0, lastIndexOfRightBracket + 1).replace("[", "-").replace("]", "-");
        String[] timesArray = times.split("-");
        List<LyricRow> lyricRows = new ArrayList<>();
        for (String tem : timesArray) {
            if (TextUtils.isEmpty(tem.trim())) {
                continue;
            }
            try {
                LyricRow lrcRow = new LyricRow(tem, formatTime(tem), content);
                lyricRows.add(lrcRow);
            } catch (Exception e) {
                LoggerUtil.showLog(TAG, e.getMessage(), 5);
            }
        }

        return lyricRows;
    }

    /****
     * 把歌词时间转换为毫秒值  如 将00:10.00  转为10000
     *
     * @param timeStr
     * @return
     */
    private static int formatTime(String timeStr) {
        timeStr = timeStr.replace('.', ':');
        String[] times = timeStr.split(":");

        return Integer.parseInt(times[0]) * 60 * 1000
                + Integer.parseInt(times[1]) * 1000
                + Integer.parseInt(times[2]);
    }

    private OnSeekToListener onSeekToListener;

    public void setOnSeekToListener(OnSeekToListener onSeekToListener) {
        this.onSeekToListener = onSeekToListener;
    }

    public interface OnSeekToListener {
        void onSeekTo(int progress);
    }

    private OnLrcClickListener onLrcClickListener;

    public void setOnLrcClickListener(OnLrcClickListener onLrcClickListener) {
        this.onLrcClickListener = onLrcClickListener;
    }

    public interface OnLrcClickListener {
        void onClick();
    }
}
