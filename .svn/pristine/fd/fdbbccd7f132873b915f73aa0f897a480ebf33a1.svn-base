package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.MusicInfo;

import java.util.List;

/**
 * 单曲 适配器
 */

public class SingleMusicAdpater extends BaseAdapter {

    private Context mContext;

    private List<MusicInfo> mMusicList;

    private LayoutInflater mInflater;

    public SingleMusicAdpater(Context context, List<MusicInfo> musicList) {
        this.mContext = context;
        this.mMusicList = musicList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMusicList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMusicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_single_music, null);
            viewHolder = new ViewHolder();
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.tv_music_title);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.tv_music_artist);
            viewHolder.moreIcon = (ImageView) convertView.findViewById(R.id.icon_more);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MusicInfo musicInfo = (MusicInfo) getItem(position);

        viewHolder.musicTitle.setText(musicInfo.getMusicTitle());
        viewHolder.musicArtist.setText(musicInfo.getMusicArtist() + "-" + musicInfo.getMusicAlbum());
        // 点击后，底部弹出PopupWindow
        viewHolder.moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    public static class ViewHolder {
        public TextView musicTitle;
        public TextView musicArtist;
        public ImageView moreIcon;
    }
}
