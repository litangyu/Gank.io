package com.litangyu.gankio.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.litangyu.gankio.R;

/**
 * 描述
 * 自定义WebView
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/17 下午4:10</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class CustomWebView extends WebView implements View.OnLongClickListener {

    private MyWebViewClient webViewClient;
    private JsInterface mJsInterface;
    private LongClickCallBack mCallBack;
    private Context mContext;

    public CustomWebView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        // 初始化设置
        WebSettings mSettings = this.getSettings();
        mSettings.setJavaScriptEnabled(true);//开启javascript
        mSettings.setDomStorageEnabled(true);//开启DOM
        mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
        mSettings.setAllowFileAccess(true);//设置支持文件流
        mSettings.setSupportZoom(true);// 支持缩放
        mSettings.setBuiltInZoomControls(true);// 支持缩放
        mSettings.setUseWideViewPort(true);// 调整到适合webview大小
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        mSettings.setLoadWithOverviewMode(true);//自适应屏幕
        mSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        mSettings.setBlockNetworkImage(true);
        mSettings.setAppCacheEnabled(true);//开启缓存机制

        webViewClient = new MyWebViewClient();
        mJsInterface = new JsInterface();
        setWebViewClient(webViewClient);
        setOnLongClickListener(this);
        addJavascriptInterface(mJsInterface, mContext.getString(R.string.js_interface_name));
    }

    @Override
    public boolean onLongClick(View v) {
        // 长按事件监听（注意：需要实现LongClickCallBack接口并传入对象）
        final HitTestResult htr = getHitTestResult();//获取所点击的内容
        if (htr.getType() == WebView.HitTestResult.IMAGE_TYPE) {//判断被点击的类型为图片
            if (mCallBack != null) {
                mCallBack.onLongClickCallBack(htr.getExtra());
            }
        }
        return false;
    }

    private class MyWebViewClient extends WebViewClient {
        /**
         * 加载过程中 拦截加载的地址url
         *
         * @param view
         * @param url  被拦截的url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        /**
         * 页面加载过程中，加载资源回调的方法
         *
         * @param view
         * @param url
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        /**
         * 页面加载完成回调的方法
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 关闭图片加载阻塞
            view.getSettings().setBlockNetworkImage(false);
        }

        /**
         * 页面开始加载调用的方法
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
            CustomWebView.this.requestFocus();
            CustomWebView.this.requestFocusFromTouch();
        }
    }

    @Override
    public void destroy() {
        webViewClient = null;
        mCallBack = null;
        mJsInterface = null;
        mContext = null;
        super.destroy();
    }

    public void setCallBack(LongClickCallBack mCallBack) {
        if (mCallBack ==null) {
            this.mCallBack = mCallBack;
        }
    }

    /**
     * 长按事件回调接口，传递图片地址
     *
     * @author LinZhang
     */
    public interface LongClickCallBack {
        /**
         * 用于传递图片地址
         */
        void onLongClickCallBack(String imgUrl);
    }

    private final static class JsInterface {

        @JavascriptInterface
        public void navigationMethod() {

        }
    }
}
