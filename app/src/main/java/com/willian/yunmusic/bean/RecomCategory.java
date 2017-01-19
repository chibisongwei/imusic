package com.willian.yunmusic.bean;

import java.util.List;

/**
 * 推荐类别
 */
public class RecomCategory {
    // 类别Icon
    private int categoryIcon;
    // 类别名称
    private String categoryName;
    // 封面集合
    private List<CoverItem> coverItems;

    public RecomCategory(int categoryIcon, String categoryName, List<CoverItem> coverItems) {
        this.categoryIcon = categoryIcon;
        this.categoryName = categoryName;
        this.coverItems = coverItems;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CoverItem> getCoverItems() {
        return coverItems;
    }

    public void setCoverItems(List<CoverItem> coverItems) {
        this.coverItems = coverItems;
    }
}
