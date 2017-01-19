package com.willian.yunmusic.constant;

/**
 * 常量定义类
 */
public class Constant {
    // 是否打印日志
    public static final boolean IS_SHOW_LOG = true;

    public static final class PlayMode {
        // 无效
        public static final int STATUS_INVALID = -1;
        // 无音乐文件
        public static final int STATUS_NOFILE = 0;
        // 准备就绪
        public static final int STATUS_PREPARE = 1;
        // 正在播放
        public static final int STATUS_PLAYING = 2;
        // 暂停播放
        public static final int STATUS_PAUSE = 3;
    }

    /**
     * 广播动作
     */
    public static final class Action {
        // 清空播放列表
        public static final String EMPTY_PLAY_QUEUE = "com.willian.yunmusic.emptyplayqueue";
        // 改变播放列表
        public static final String PLAY_QUEUE_CHANGED = "com.willian.yunmusic.playqueuechanged";
        // 歌曲准备完毕
        public static final String MUSIC_PREPARED = "com.willian.yunmusic.musicprepared";
        // 更新歌曲信息
        public static final String UPDATE_MUSIC_INFO = "com.willian.yunmusic.updatemusicinfo";
        // 更新歌曲缓冲进度
        public static final String UPDATE_BUFFER_PROGRESS = "com.willian.yunmusic.updatebufferprogress";
        // 歌曲数量变化
        public static final String MUSIC_COUNT_CHANGED = "com.willian.yunmusic.musiccountchanged";

        public static final String META_CHANGED = "com.willian.yunmusic.metachanged";
    }
}
