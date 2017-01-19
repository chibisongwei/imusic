package com.willian.yunmusic.util;

import com.willian.yunmusic.bean.LineInfo;
import com.willian.yunmusic.bean.LyricInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 歌词工具类
 */

public class LyricUtil {

    /**
     * 初始化歌词信息
     *
     * @param inputStream
     * @param charsetName
     */
    public static void initLyricResource(InputStream inputStream, String charsetName) {
        try {
            LyricInfo lyricInfo = new LyricInfo();
            List<LineInfo> lines = new ArrayList<>();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                analyzeLyric(lyricInfo, line, lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析歌词
     *
     * @param lyricInfo
     * @param line
     */
    private static void analyzeLyric(LyricInfo lyricInfo, String line, List<LineInfo> lines) {
        int index = line.lastIndexOf("]");
        if (line != null && line.startsWith("[ti:")) {
            // 标题
            String titleStr = line.substring(4, index).trim();
            lyricInfo.setTitle(titleStr);
            return;
        }
        if (line != null && line.startsWith("[ar:")) {
            // 作者
            String authorStr = line.substring(4, index).trim();
            lyricInfo.setAuthor(authorStr);
            return;
        }
        if (line != null && line.startsWith("[al:")) {
            // 专辑
            String albumStr = line.substring(4, index).trim();
            lyricInfo.setAlbum(albumStr);
            return;
        }
        if (line != null && line.startsWith("[by:")) {
            return;
        }
        if (line != null && line.startsWith("[offset:")) {
            // 时间偏移量
            String offsetStr = line.substring(8, index);
            lyricInfo.setOffset(Long.parseLong(offsetStr));
            return;
        }
        if (line != null && index == 9 && line.length() > 10) {
            // 歌词内容
            LineInfo lineInfo = new LineInfo();
            lineInfo.setStartTime(getStartTimeMillis(line.substring(0, 10)));
            lineInfo.setContent(line.substring(10, line.length()));
            lines.add(lineInfo);
            lyricInfo.setLines(lines);
        }
    }

    /**
     * 获取时间的微秒值
     *
     * @return
     */
    private static long getStartTimeMillis(String str) {
        long minute = Long.parseLong(str.substring(1, 3));
        long second = Long.parseLong(str.substring(4, 6));
        long milliSecond = Long.parseLong(str.substring(7, 9));
        return milliSecond + second * 1000 * minute * 60 * 1000;
    }
}
