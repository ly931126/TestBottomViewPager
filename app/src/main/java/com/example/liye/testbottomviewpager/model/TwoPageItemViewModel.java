package com.example.liye.testbottomviewpager.model;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import com.example.liye.testbottomviewpager.entity.TwoItemInfo;
import com.kelin.mvvmlight.base.ViewModel;

/**
 * Created by liye on 2018/1/25.
 */

public class TwoPageItemViewModel implements ViewModel {

    private Context mContext = null;

    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<Drawable> mIcon = new ObservableField<>();

    public TwoPageItemViewModel(Context context, TwoItemInfo itemInfo) {
        this.mContext = context;
        mTitle.set(itemInfo.getTitle());
        mIcon.set(itemInfo.getIcon());
    }
}
