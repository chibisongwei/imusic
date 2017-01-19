package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.CommItem;

import java.util.List;

/**
 * Item 适配器
 */
public class SongListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommItem> mItemList;
    private LayoutInflater mInflater;

    public SongListAdapter(Context mContext, List<CommItem> mList) {
        this.mContext = mContext;
        this.mItemList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_song_list, null);
            viewHolder = new ViewHolder();
            viewHolder.leftIcon = (ImageView) convertView.findViewById(R.id.iv_song_list_icon);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.tv_song_list_name);
            viewHolder.itemCaption = (TextView) convertView.findViewById(R.id.tv_song_count);
            viewHolder.rightIcon = (ImageView) convertView.findViewById(R.id.iv_list_more_icon);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommItem itemInfo = mItemList.get(position);

        viewHolder.leftIcon.setImageResource(itemInfo.getLeftResId());
        viewHolder.itemName.setText(itemInfo.getItemName());
        viewHolder.itemCaption.setText(itemInfo.getItemVerCaption());
        viewHolder.rightIcon.setImageResource(itemInfo.getRightResId());

        return convertView;
    }

    /**
     * 刷新ListView的数据
     *
     * @param itemInfoList
     */
    public void changeData(List<CommItem> itemInfoList) {
        this.mItemList = itemInfoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public ImageView leftIcon;
        public TextView itemName;
        public TextView itemCaption;
        public ImageView rightIcon;
    }
}
