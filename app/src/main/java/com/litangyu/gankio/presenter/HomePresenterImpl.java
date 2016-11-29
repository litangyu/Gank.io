package com.litangyu.gankio.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.litangyu.basemvp.base.AppBasePresenter;
import com.litangyu.gankio.R;
import com.litangyu.gankio.ui.fragments.AndroidFragment;
import com.litangyu.gankio.ui.fragments.WebFragment;
import com.litangyu.gankio.ui.fragments.iOSFragment;
import com.litangyu.gankio.view.HomeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/24 上午11:15</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class HomePresenterImpl extends AppBasePresenter<HomeView> {

    private Context mContext;

    @Override
    public void initialized(Context context) {
        this.mContext = context;
    }

    public List<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        AndroidFragment androidFragment = new AndroidFragment();
        iOSFragment iOSFragment = new iOSFragment();
        WebFragment webFragment = new WebFragment();

        androidFragment.setPresenter(this);
        iOSFragment.setPresenter(this);
        webFragment.setPresenter(this);

        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(webFragment);

        return fragments;
    }

    public List<String> getTitles() {
        return Arrays.asList(mContext.getResources().getStringArray(R.array.home_title));
    }
}
