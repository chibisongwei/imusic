package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.MusicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 播放列表适配器
 */

public class PlayQueueAdapter extends BaseAdapter {

    private Context mContext;

    private List<MusicInfo> mPlayList = new ArrayList<>();

    private LayoutInflater mInflater;

    public PlayQueueAdapter(Context context, List<MusicInfo> playList) {
        this.mContext = context;
        this.mPlayList = playList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mPlayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.common_listview_item, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {

    }
}
