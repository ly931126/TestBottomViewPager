package com.example.liye.testbottomviewpager.model;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;

import com.example.liye.testbottomviewpager.BR;
import com.example.liye.testbottomviewpager.R;
import com.example.liye.testbottomviewpager.entity.ItemInfo;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;

import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by liye on 2018/1/19.
 */

public class MainViewModel implements ViewModel, ViewPager.OnPageChangeListener {

    private Context mContext = null;
    private ViewPager mViewPager = null;
    public final ObservableList<ViewPagerItemViewModel> mItems = new ObservableArrayList<>();
    public final OnItemBind<ViewPagerItemViewModel> mItemBinding = (itemBinding, position, item) -> itemBinding.set(BR.viewModel, position == 0 ? R.layout.page_item_one : position == 1 ? R.layout.page_item_two : position == 2 ? R.layout.page_item_three : position == 3 ? R.layout.page_item_four : R.layout.page_item_one);

//    public final ObservableList<BottomItemViewModel> mBottomItems = new ObservableArrayList<>();
//    public final ItemBinding<BottomItemViewModel> mBottomItemBinding = ItemBinding.of(BR.viewModel, R.layout.bottom_item);


    public ObservableField<Drawable> mImageOne = new ObservableField<>();
    public ObservableField<Drawable> mImageTwo = new ObservableField<>();
    public ObservableField<Drawable> mImageThree = new ObservableField<>();
    public ObservableField<Drawable> mImageFour = new ObservableField<>();
    public ObservableField<Integer> mTextOne = new ObservableField<>();
    public ObservableField<Integer> mTextTwo = new ObservableField<>();
    public ObservableField<Integer> mTextThree = new ObservableField<>();
    public ObservableField<Integer> mTextFour = new ObservableField<>();


    public MainViewModel(Context context, ViewPager viewPager ) {
        this.mContext = context;
        this.mViewPager = viewPager;
        loadItemList();
        viewPager.setOnPageChangeListener(this);
        initBottomLayout();

    }

    private void initBottomLayout() {
        mImageOne.set(mContext.getResources().getDrawable(R.drawable.chats_green));
        mTextOne.set(mContext.getResources().getColor(R.color.colorAccent));
        mImageTwo.set(mContext.getResources().getDrawable(R.drawable.contacts));
        mImageThree.set(mContext.getResources().getDrawable(R.drawable.discover));
        mImageFour.set(mContext.getResources().getDrawable(R.drawable.about_me));
        // TextView置为白色
        mTextTwo.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextThree.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextFour.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
    }

    private void loadItemList() {
        mItems.clear();

        int mPageCount = 4;
        for (int i = 0; i < mPageCount; i++) {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setId(i);
            mItems.add(new ViewPagerItemViewModel(mContext, itemInfo));

        }

    }

    public ReplyCommand mBottomOneClickCommand = new ReplyCommand(() -> {
        restartBotton();
        mImageOne.set(mContext.getResources().getDrawable(R.drawable.chats_green));
        mTextOne.set(mContext.getResources().getColor(R.color.colorAccent));
        mViewPager.setCurrentItem(0);
    });

    public ReplyCommand mBottomTwoClickCommand = new ReplyCommand(() -> {
        restartBotton();
        mImageTwo.set(mContext.getResources().getDrawable(R.drawable.contacts_green));
        mTextTwo.set(mContext.getResources().getColor(R.color.colorAccent));
        mViewPager.setCurrentItem(1);

    });

    public ReplyCommand mBottomThreeClickCommand = new ReplyCommand(() -> {
        restartBotton();

        mImageThree.set(mContext.getResources().getDrawable(R.drawable.discover_green));
        mTextThree.set(mContext.getResources().getColor(R.color.colorAccent));
        mViewPager.setCurrentItem(2);

    });

    public ReplyCommand mBottomFourClickCommand = new ReplyCommand(() -> {
        restartBotton();

        mImageFour.set(mContext.getResources().getDrawable(R.drawable.about_me_green));
        mTextFour.set(mContext.getResources().getColor(R.color.colorAccent));
        mViewPager.setCurrentItem(3);

    });

    private void restartBotton() {
        // ImageView置为灰色
        mImageOne.set(mContext.getResources().getDrawable(R.drawable.chats));
        mImageTwo.set(mContext.getResources().getDrawable(R.drawable.contacts));
        mImageThree.set(mContext.getResources().getDrawable(R.drawable.discover));
        mImageFour.set(mContext.getResources().getDrawable(R.drawable.about_me));
        // TextView置为白色
        mTextOne.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextTwo.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextThree.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mTextFour.set(mContext.getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        restartBotton();
        //当前view被选择的时候,改变底部菜单图片，文字颜色
        switch (arg0) {
            case 0:
                mImageOne.set(mContext.getResources().getDrawable(R.drawable.chats_green));
                mTextOne.set(mContext.getResources().getColor(R.color.colorAccent));
                break;
            case 1:
                mImageTwo.set(mContext.getResources().getDrawable(R.drawable.contacts_green));
                mTextTwo.set(mContext.getResources().getColor(R.color.colorAccent));
                break;
            case 2:
                mImageThree.set(mContext.getResources().getDrawable(R.drawable.discover_green));
                mTextThree.set(mContext.getResources().getColor(R.color.colorAccent));
                break;
            case 3:
                mImageFour.set(mContext.getResources().getDrawable(R.drawable.about_me_green));
                mTextFour.set(mContext.getResources().getColor(R.color.colorAccent));
                break;

            default:
                break;
        }

    }


}
