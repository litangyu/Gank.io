package com.litangyu.gankio.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litangyu.basemvp.base.AppBasePresenter;
import com.litangyu.basemvp.ui.BaseLazyLoadFragment;
import com.litangyu.gankio.presenter.HomePresenterImpl;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 下午4:25</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class WebFragment extends BaseLazyLoadFragment {

    private RecyclerView mRv_android;
    private HomePresenterImpl mPresenter;

    public void setPresenter(AppBasePresenter basePresenter) {
        this.mPresenter = (HomePresenterImpl) basePresenter;
    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public View getLoadingTargetView() {
        return null;
    }

}
