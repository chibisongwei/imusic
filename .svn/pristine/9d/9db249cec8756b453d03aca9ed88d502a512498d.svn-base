package com.willian.yunmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.willian.yunmusic.BaseApplication;
import com.willian.yunmusic.R;
import com.willian.yunmusic.activity.PlayingActivity;
import com.willian.yunmusic.bean.MusicInfo;
import com.willian.yunmusic.util.HandlerUtil;
import com.willian.yunmusic.util.PlayUtil;

/**
 * 位于界面底部的歌曲播放控制器
 */

public class QuickControlFragment extends BaseFragment implements View.OnClickListener {

    private View mView;

    private ImageView mCoverImage;

    private TextView mNameText;

    private TextView mArtistText;

    // 播放列表
    private ImageView mPlayQueue;
    // 播放或停止
    private ImageView mPlayControl;
    // 下一首
    private ImageView mPlayNext;
    // 播放进度
    private ProgressBar mProgressBar;

    private static QuickControlFragment mControlFragment;

    public static QuickControlFragment getInstance() {
        if (mControlFragment == null) {
            mControlFragment = new QuickControlFragment();
        }
        return mControlFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.include_bottom_music, container, false);

        initView();

        handleEvent();

        return mView;
    }

    private void handleEvent() {
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开音乐播放界面
                Intent intent = new Intent(BaseApplication.mContext, PlayingActivity.class);
                // singleTask启动模式
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.mContext.startActivity(intent);
            }
        });
    }

    private void initView() {
        mCoverImage = (ImageView) mView.findViewById(R.id.iv_card_cover);
        mNameText = (TextView) mView.findViewById(R.id.tv_card_name);
        mArtistText = (TextView) mView.findViewById(R.id.tv_card_artist);
        mPlayQueue = (ImageView) mView.findViewById(R.id.iv_play_list);
        mPlayControl = (ImageView) mView.findViewById(R.id.iv_play_control);
        mPlayNext = (ImageView) mView.findViewById(R.id.iv_play_next);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.pb_music_play);
        mProgressBar.setMax((int) PlayUtil.getDuration());
        mProgressBar.setProgress((int) PlayUtil.getPosition());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_list:
                // 弹出播放列表
                PlayQueueFragment playQueueFragment = new PlayQueueFragment();
                playQueueFragment.show(getFragmentManager(), "play_queue");
                break;
            case R.id.iv_play_control:
                mPlayControl.setImageResource(PlayUtil.isPlaying() ? R.mipmap.playbar_btn_pause : R.mipmap.playbar_btn_play);
                // 播放或暂停
                HandlerUtil.getInstance(BaseApplication.mContext).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PlayUtil.playOrPause();
                    }
                }, 60);
                break;
            case R.id.iv_play_next:
                // 下一首
                HandlerUtil.getInstance(getActivity()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PlayUtil.playNext();
                    }
                }, 60);
                break;
        }
    }

    /**
     * 更新歌曲信息
     *
     * @param musicInfo
     */
    @Override
    public void updateMusicInfo(MusicInfo musicInfo) {
        super.updateMusicInfo(musicInfo);
        // 歌曲名
        mNameText.setText(musicInfo.getMusicTitle());
        // 艺术家
        mArtistText.setText(musicInfo.getMusicArtist());
        // 更新进度
        mProgressBar.setMax((int) PlayUtil.getDuration());
        mProgressBar.postDelayed(mUpdateProgress, 10);
    }

    /**
     * 更新歌曲时长
     */
    @Override
    public void updateMusicTime() {
        super.updateMusicTime();
        mProgressBar.setMax((int) PlayUtil.getDuration());
    }

    private Runnable mUpdateProgress = new Runnable() {
        @Override
        public void run() {
            mProgressBar.setMax((int) PlayUtil.getDuration());
            mProgressBar.setProgress((int) PlayUtil.getPosition());
            if (PlayUtil.isPlaying()) {
                mProgressBar.postDelayed(mUpdateProgress, 50);
            } else {
                mProgressBar.removeCallbacks(this);
            }
        }
    };
}
