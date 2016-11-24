package com.litangyu.gankio.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.DownloadListener;

import com.litangyu.gankio.R;
import com.litangyu.gankio.widget.CustomWebView;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/17 下午4:08</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class WebViewActivity extends AppCompatActivity{

    private CustomWebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        mWebView = (CustomWebView) findViewById(R.id.web);
        //设置WebView下载监听，当有下载时调用系统相关功能
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                WebViewActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mWebView.setVisibility(View.GONE);
        mWebView.pauseTimers();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
