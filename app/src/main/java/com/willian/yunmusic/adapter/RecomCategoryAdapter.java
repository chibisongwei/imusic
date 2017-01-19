package com.willian.yunmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.willian.yunmusic.R;
import com.willian.yunmusic.bean.CoverItem;
import com.willian.yunmusic.bean.RecomCategory;

import java.util.List;

/**
 * Created by willian on 2016/8/10.
 */
public class RecomCategoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<RecomCategory> mCategoryList;
    private LayoutInflater mInflater;

    public RecomCategoryAdapter(Context context, List<RecomCategory> categoryList) {
        this.mCategoryList = categoryList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_category, null);
            viewHolder = new ViewHolder();
            viewHolder.categoryIcon = (ImageView) convertView.findViewById(R.id.iv_category_icon);
            viewHolder.categoryName = (TextView) convertView.findViewById(R.id.tv_category_name);
            viewHolder.coverGridView = (GridView) convertView.findViewById(R.id.gv_cover);
            // 第二次绘制ListView时，直接从getTag()中取出
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RecomCategory recomCategory = mCategoryList.get(position);

        viewHolder.categoryIcon.setImageResource(recomCategory.getCategoryIcon());
        viewHolder.categoryName.setText(recomCategory.getCategoryName());

        // 获取封面信息
        List<CoverItem> coverItems = recomCategory.getCoverItems();
        if (coverItems != null && coverItems.size() > 0) {
            CoverItemAdapter coverItemAdapter = new CoverItemAdapter(mContext, coverItems);
            viewHolder.coverGridView.setAdapter(coverItemAdapter);
        }

        return convertView;
    }

    public static class ViewHolder {
        public ImageView categoryIcon;
        public TextView categoryName;
        public GridView coverGridView;
    }
}
