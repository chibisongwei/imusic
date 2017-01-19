package com.willian.yunmusic.util;

import com.willian.yunmusic.bean.MusicInfo;

import java.util.List;

/**
 * Created by willian on 2016/9/26.
 */

public class MusicUtil {

    /**
     * 根据歌曲的ID，寻找出歌曲在当前播放列表中的位置
     *
     * @return
     */
    public static int seekPosInListById(List<MusicInfo> musicList, int musicId) {
        int pos = -1;
        if (musicId == -1) {
            return -1;
        }
        if (musicList != null) {
            for (int i = 0; i < musicList.size(); i++) {
                if (musicId == musicList.get(i).getMusicId()) {
                    pos = i;
                    break;
                }
            }
        }
        return pos;
    }
}
