package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.CommItem;

import java.util.List;

/**
 * 电台
 */
public class RadioAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommItem> mItemList;
    private LayoutInflater mInflater;
    private int itemWidth;

    public RadioAdapter(Context context, List<CommItem> itemList, int itemWidth) {
        this.mContext = context;
        this.mItemList = itemList;
        this.itemWidth = itemWidth;
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
            convertView = mInflater.inflate(R.layout.gridview_item_radio, null);
            viewHolder = new ViewHolder();
            viewHolder.itemlayout = (LinearLayout) convertView.findViewById(R.id.layout_radio_item);
            viewHolder.radioIcon = (ImageView) viewHolder.itemlayout.findViewById(R.id.iv_radio_icon);
            viewHolder.radioName = (TextView) viewHolder.itemlayout.findViewById(R.id.tv_radio_name);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 设置ImageView的宽度和高度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, itemWidth);
        //viewHolder.itemlayout.setPadding(itemWidth / 5, itemWidth / 5, itemWidth / 5, itemWidth / 5);
        viewHolder.itemlayout.setLayoutParams(params);

        CommItem itemInfo = mItemList.get(position);

        viewHolder.radioIcon.setImageResource(itemInfo.getLeftResId());
        viewHolder.radioName.setText(itemInfo.getItemName());

        return convertView;
    }

    public static class ViewHolder {
        public LinearLayout itemlayout;
        public ImageView radioIcon;
        public TextView radioName;
    }
}
