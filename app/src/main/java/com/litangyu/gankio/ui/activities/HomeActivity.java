package com.litangyu.gankio.ui.activities;

import android.view.View;

import com.litangyu.gankio.R;
import com.litangyu.gankio.base.BaseActivity;
import com.litangyu.gankio.presenter.HomePresenter;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 上午11:14</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class HomeActivity extends BaseActivity<HomePresenter> {
    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
