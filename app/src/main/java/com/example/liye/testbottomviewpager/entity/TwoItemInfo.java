package com.example.liye.testbottomviewpager.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by liye on 2018/1/25.
 */

public class TwoItemInfo {

    private int id;



    private String  title;

    private Drawable icon;

    private Integer textColor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Integer getTextColor() {
        return textColor;
    }

    public void setTextColor(Integer textColor) {
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
