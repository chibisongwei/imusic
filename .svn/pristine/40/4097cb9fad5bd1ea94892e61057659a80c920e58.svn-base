package com.willian.yunmusic.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.bean.RecomCategory;

import java.util.List;

/**
 * Created by willian on 2016/8/10.
 */
public class CoverItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<CoverItem> mCoverList;
    private LayoutInflater mInflater;

    public CoverItemAdapter(Context context, List<CoverItem> coverList) {
        this.mCoverList = coverList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCoverList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCoverList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gridview_item_cover, null);
            viewHolder = new ViewHolder();
            viewHolder.coverImage = (ImageView) convertView.findViewById(R.id.iv_cover);
            viewHolder.coverName = (TextView) convertView.findViewById(R.id.tv_song);
            viewHolder.coverSinger = (TextView) convertView.findViewById(R.id.tv_singer);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GridView mGridView = (GridView) parent;
        // 获取item之间的水平间距
        int horizontalSpacing = mGridView.getHorizontalSpacing();
        // 获取列数
        int numColums = mGridView.getNumColumns();
        // 计算每个item的宽度
        int itemWidth = (mGridView.getWidth() - (numColums - 1) * horizontalSpacing
                - mGridView.getPaddingLeft() - mGridView.getPaddingRight()) / numColums;
        // 设置ImageView的宽度和高度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, itemWidth);
        viewHolder.coverImage.setLayoutParams(params);

        CoverItem coverItem = (CoverItem) getItem(position);
        viewHolder.coverImage.setImageResource(coverItem.getCoverImage());
        viewHolder.coverName.setText(coverItem.getCoverName());
        String coverSinger = coverItem.getCoverSinger();
        if (!TextUtils.isEmpty(coverSinger)) {
            viewHolder.coverSinger.setVisibility(View.VISIBLE);
            viewHolder.coverSinger.setText(coverItem.getCoverSinger());
        } else {
            viewHolder.coverSinger.setVisibility(View.GONE);
        }

        return convertView;
    }

    public static class ViewHolder {
        public ImageView coverImage;
        public TextView coverName;
        public TextView coverSinger;
    }
}
