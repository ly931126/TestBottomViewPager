package com.example.liye.testbottomviewpager;

import com.example.liye.testbottomviewpager.databinding.ActivityMainBinding;
import com.example.liye.testbottomviewpager.model.MainViewModel;


public class MainActivity extends BaseActivity<ActivityMainBinding>  {

    @Override
    protected void init() {
        mViewBinding.setVariable(BR.viewModel,new MainViewModel(this,mViewBinding.vpContent ));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

}
