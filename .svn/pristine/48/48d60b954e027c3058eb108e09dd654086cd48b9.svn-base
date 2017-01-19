package com.willian.yunmusic.adapter;

import android.content.Context;
import android.text.TextUtils;
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
public class OfficialTopAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommItem> mItemList;
    private LayoutInflater mInflater;

    public OfficialTopAdapter(Context mContext, List<CommItem> mList) {
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
            convertView = mInflater.inflate(R.layout.listview_item_official_top, null);
            viewHolder = new ViewHolder();
            viewHolder.leftIcon = (ImageView) convertView.findViewById(R.id.iv_left);
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.tv_first);
            viewHolder.secondName = (TextView) convertView.findViewById(R.id.tv_second);
            viewHolder.thirdName = (TextView) convertView.findViewById(R.id.tv_third);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommItem itemInfo = mItemList.get(position);

        viewHolder.leftIcon.setImageResource(itemInfo.getLeftResId());
        viewHolder.firstName.setText(itemInfo.getItemName());
        viewHolder.secondName.setText(itemInfo.getItemVerCaption());
        viewHolder.thirdName.setText(itemInfo.getItemHorCaption());

        return convertView;
    }

    public static class ViewHolder {
        public ImageView leftIcon;
        public TextView firstName;
        public TextView secondName;
        public TextView thirdName;
    }
}
