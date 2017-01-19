package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.CommItem;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.widget.WrapGridView;

import java.util.List;

/**
 * 电台
 */
public class RadioItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<List<CoverItem>> mGridList;
    private LayoutInflater mInflater;

    public RadioItemAdapter(Context context, List<List<CoverItem>> gridList) {
        this.mContext = context;
        this.mGridList = gridList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mGridList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGridList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_radio, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (WrapGridView) convertView.findViewById(R.id.gv_radio_item);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        List<CoverItem> itemList = mGridList.get(position);
        CoverItemAdapter coverItemAdapter = new CoverItemAdapter(mContext, itemList);
        viewHolder.gridView.setAdapter(coverItemAdapter);

        return convertView;
    }

    public static class ViewHolder {
        public WrapGridView gridView;
    }
}
