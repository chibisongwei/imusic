package com.willian.yunmusic.bean;

/**
 * CommItem 实体类
 */
public class CommItem {
    // Item 左侧Icon
    private int leftResId;
    // Item 名称
    private String itemName;
    // Item 横向备注
    private String itemVerCaption;
    // Item 纵向备注
    private String itemHorCaption;
    // Item 右侧Icon
    private int rightResId;

    public CommItem(int leftResId, String itemName) {
        this.leftResId = leftResId;
        this.itemName = itemName;
    }

    public CommItem(int leftResId, String itemName, int rightResId) {
        this.leftResId = leftResId;
        this.itemName = itemName;
        this.rightResId = rightResId;
    }

    public CommItem(int leftResId, String itemName, String itemVerCaption, String itemHorCaption) {
        this.leftResId = leftResId;
        this.itemName = itemName;
        this.itemVerCaption = itemVerCaption;
        this.itemHorCaption = itemHorCaption;
    }

    public CommItem(int leftResId, String itemName, String itemVerCaption, String itemHorCaption, int rightResId) {
        this.leftResId = leftResId;
        this.itemName = itemName;
        this.itemVerCaption = itemVerCaption;
        this.itemHorCaption = itemHorCaption;
        this.rightResId = rightResId;
    }

    public int getLeftResId() {
        return leftResId;
    }

    public void setLeftResId(int leftResId) {
        this.leftResId = leftResId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemVerCaption() {
        return itemVerCaption;
    }

    public void setItemVerCaption(String itemVerCaption) {
        this.itemVerCaption = itemVerCaption;
    }

    public String getItemHorCaption() {
        return itemHorCaption;
    }

    public void setItemHorCaption(String itemHorCaption) {
        this.itemHorCaption = itemHorCaption;
    }

    public int getRightResId() {
        return rightResId;
    }

    public void setRightResId(int rightResId) {
        this.rightResId = rightResId;
    }
}
