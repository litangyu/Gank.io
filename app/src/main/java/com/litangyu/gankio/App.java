/*
 * 描述
 * Created by: litangyu
 * 包含类名列表
 * 版本信息，版本号 v1.0
 * 创建日期 16/9/12
 * 版权声明
*/
package com.litangyu.gankio;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.litangyu.gankio.service.GankIoService;
import com.litangyu.gankio.util.ActivityUtil;
import com.litangyu.gankio.util.ConnectUtil;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述
 * Created by: litangyu<br/>
 * Created on: 16/9/12 下午4:53
 */
public class App extends MultiDexApplication {

    private static App mInstance;
    private Context context;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App context() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ConnectUtil.init();
    }

    /**
     * 初始化屏幕宽高
     */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        ActivityUtil.closeAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
