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
public class CommItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommItem> mItemList;
    private LayoutInflater mInflater;

    public CommItemAdapter(Context mContext, List<CommItem> mList) {
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
            convertView = mInflater.inflate(R.layout.common_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.leftIcon = (ImageView) convertView.findViewById(R.id.iv_left_icon);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.tv_item_name);
            viewHolder.itemVerCaption = (TextView) convertView.findViewById(R.id.tv_ver_caption);
            viewHolder.itemHorCaption = (TextView) convertView.findViewById(R.id.tv_hor_caption);
            viewHolder.rightIcon = (ImageView) convertView.findViewById(R.id.iv_right_icon);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommItem itemInfo = mItemList.get(position);

        viewHolder.leftIcon.setImageResource(itemInfo.getLeftResId());
        viewHolder.itemName.setText(itemInfo.getItemName());

        if (!TextUtils.isEmpty(itemInfo.getItemVerCaption())) {
            viewHolder.itemVerCaption.setVisibility(View.VISIBLE);
            viewHolder.itemVerCaption.setText(itemInfo.getItemVerCaption());
        } else {
            viewHolder.itemVerCaption.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(itemInfo.getItemHorCaption())) {
            viewHolder.itemHorCaption.setVisibility(View.VISIBLE);
            viewHolder.itemHorCaption.setText(itemInfo.getItemHorCaption());
        } else {
            viewHolder.itemHorCaption.setVisibility(View.GONE);
        }

        if (itemInfo.getRightResId() != 0) {
            viewHolder.rightIcon.setVisibility(View.VISIBLE);
            viewHolder.rightIcon.setImageResource(itemInfo.getRightResId());
        } else {
            viewHolder.rightIcon.setVisibility(View.GONE);
        }

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
        public TextView itemVerCaption;
        public TextView itemHorCaption;
        public ImageView rightIcon;
    }
}
