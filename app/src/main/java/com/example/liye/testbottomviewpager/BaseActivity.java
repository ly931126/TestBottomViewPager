package com.example.liye.testbottomviewpager;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {

    protected SV mViewBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState = null;
        }
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, getContentViewId());
        init();
    }

    /**
     * init data or view
     */
    protected abstract void init();

    /**
     * set activity layoutId
     *
     * @return
     */
    protected abstract int getContentViewId();

}
