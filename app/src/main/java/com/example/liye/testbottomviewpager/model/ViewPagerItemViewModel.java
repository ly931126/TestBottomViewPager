package com.example.liye.testbottomviewpager.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.widget.Toast;

import com.example.liye.testbottomviewpager.BR;
import com.example.liye.testbottomviewpager.R;
import com.example.liye.testbottomviewpager.entity.ItemInfo;
import com.example.liye.testbottomviewpager.entity.TwoItemInfo;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by liye on 2018/1/19.
 */

public class ViewPagerItemViewModel implements ViewModel {

    private Context mContext = null;
    public final ObservableList<TwoPageItemViewModel> mItems = new ObservableArrayList<>();
    public final ItemBinding<TwoPageItemViewModel> mItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_page_two);


    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mEditText = new ObservableField<>();

    public ViewPagerItemViewModel(Context context, ItemInfo itemInfo) {
        this.mContext = context;
        if (itemInfo.getId() == 0) {
            mEditText.set(null);
            initOnePage();
        } else if (itemInfo.getId() == 1) {
            initTwoPage();
        }
    }


    private void initOnePage() {
        mContent.set("beijin");

    }

    private void initTwoPage() {
        mItems.clear();
        Observable.from(getTwoItemList()).subscribe(new Subscriber<TwoItemInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TwoItemInfo settingItemInfo) {
                mItems.add(new TwoPageItemViewModel(mContext, settingItemInfo));
            }
        });

    }

    private List<TwoItemInfo> getTwoItemList() {
        ArrayList<TwoItemInfo> topItemInfoList = new ArrayList<>();

        Resources res = mContext.getResources();
        TypedArray menuIconArray = res.obtainTypedArray(R.array.top_title_icon_array);
        String[] menuTitleArray = res.getStringArray(R.array.main_menu_title_array);
        for (int i = 0; i < menuIconArray.length(); i++) {

            TwoItemInfo appItemInfo = new TwoItemInfo();
            appItemInfo.setIcon(menuIconArray.getDrawable(i));
            appItemInfo.setTitle(menuTitleArray[i]);

            topItemInfoList.add(appItemInfo);
        }
        menuIconArray.recycle();
        return topItemInfoList;
    }

    public final ReplyCommand<String> textWatcher = new ReplyCommand<>((s) -> {
        if (s == null || "".equals(s)) {
//            setListDataByStringList();
        } else {
            getEditText(s);
        }
    });

    private void getEditText(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
    }
}
