package com.litangyu.basemvp.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.litangyu.basemvp.base.AppBasePresenter;
import com.litangyu.basemvp.base.AppRuntimeException.LayoutResIDException;
import com.litangyu.basemvp.base.Contract;

/**
 * 描述
 * Activity基类
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 16/9/19 下午2:28</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public abstract class BaseActivity<T extends AppBasePresenter> extends
        AppCompatActivity implements Contract.BaseView, android.view.View.OnClickListener {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();

        setTransparentStatusBar();

        int layoutResID = initContentView();
        if (layoutResID == 0) {
            throw new LayoutResIDException();
        } else {
            setContentView(initContentView());
        }

        bindView();
        initView();
        initData();
        setListener();
    }

    private void setTransparentStatusBar() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 初始化Presenter
     *
     * @return Presenter实现类
     */
    protected abstract T initPresenter();

    /**
     * 设置布局文件
     *
     * @return layout ID
     */
    protected abstract int initContentView();

    /**
     * 绑定控件
     */
    protected abstract void bindView();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void hideView(android.view.View view) {
        view.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public void showView(android.view.View view) {
        view.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void disappearView(android.view.View view) {
        view.setVisibility(android.view.View.GONE);
    }

    /**
     * 显示键盘
     */
    @SuppressWarnings("static-access")
    protected void showSoftInput(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);
    }

    /**
     * 隐藏键盘
     */
    @SuppressWarnings("static-access")
    protected void hideSoftInput(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
