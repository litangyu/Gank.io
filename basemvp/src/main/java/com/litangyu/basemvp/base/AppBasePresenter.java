package com.litangyu.basemvp.base;

import com.litangyu.basemvp.base.AppRuntimeException.ViewNotAttachedException;

/**
 * 描述
 * <p/>
 * Version: v1.0
 * Created by: litangyu
 * Created on: 16/9/19 下午2:15
 * Email: lty81372860@sina.com
 * Copyright © 2016年 litangyu. All rights reserved.
 * <p/>
 * Revision：
 */
public abstract class AppBasePresenter<T extends Contract.BaseView>
        implements Contract.BasePresenter<T> {

    protected T mView;

    /**
     * 绑定View
     *
     * @param view view实现类
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 解绑View
     */
    @Override
    public void detachView() {
        try {
            this.mView = null;
        } catch (ViewNotAttachedException e) {
            e.printStackTrace();
        }
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

}
