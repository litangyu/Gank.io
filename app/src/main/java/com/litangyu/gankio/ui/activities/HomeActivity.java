package com.litangyu.gankio.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.litangyu.basemvp.ui.BaseActivity;
import com.litangyu.gankio.R;
import com.litangyu.gankio.presenter.HomePresenterImpl;
import com.litangyu.gankio.ui.adapter.HomeTabAdapter;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 上午11:14</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class HomeActivity extends BaseActivity<HomePresenterImpl> {

    private TabLayout mTab_title;
    private ViewPager mVp_home_content;
    private HomeTabAdapter mHomeTabAdapter;

    @Override
    protected HomePresenterImpl initPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void bindView() {
        mTab_title = (TabLayout) findViewById(R.id.tab_title);
        mVp_home_content = (ViewPager) findViewById(R.id.vp_home_content);
    }

    @Override
    protected void initView() {
        mTab_title.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData() {
        mPresenter.initialized(this);

        mHomeTabAdapter = new HomeTabAdapter(getSupportFragmentManager(),
                mPresenter.getFragments(), mPresenter.getTitles());
        mVp_home_content.setAdapter(mHomeTabAdapter);

        mTab_title.setupWithViewPager(mVp_home_content);
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void onClick(View v) {

    }
}
