/*
 * 描述
 * Created by: litangyu
 * 包含类名列表
 * 版本信息，版本号 v1.0
 * 创建日期 16/9/12
 * 版权声明
*/
package com.litangyu.gankio.base;

public interface Contract {

    interface BaseView extends View {

        /**
         * 显示控件 View.VISIBLE
         */
        void showView(android.view.View view);

        /**
         * 隐藏控件 View.INVISIBLE
         */
        void hideView(android.view.View view);

        /**
         * 隐藏控件 View.GONE
         */
        void disappearView(android.view.View view);
    }

    interface BasePresenter<V extends View> extends Presenter {

        /**
         * 绑定View
         *
         * @param view View实现类
         */
        void attachView(V view);

        /**
         * 解绑View
         */
        void detachView();
    }
}
