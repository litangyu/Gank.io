package com.litangyu.basemvp.base.AppRuntimeException;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/10/9 上午11:14</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class ViewNotAttachedException extends RuntimeException{
    public ViewNotAttachedException() {
        //在请求数据前，请调用Presenter.attachView(View);
        super("Please call Presenter.attachView(View) before" +
                " requesting data to the Presenter");
    }
}
