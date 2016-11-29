package com.litangyu.gankio.util;

import com.litangyu.gankio.Config;
import com.litangyu.gankio.bean.EveryDayForAndroidEntity;
import com.litangyu.gankio.service.GankIoService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/29 上午10:56</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class ConnectUtil {

    private static ConnectUtil instance = null;
    private final GankIoService gankIoService;

    private ConnectUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(Config.ConnectTimeOut, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gankIoService = retrofit.create(GankIoService.class);
    }

    public static ConnectUtil getInstance() {
        if (instance == null) {
            synchronized (ConnectUtil.class) {
                if (instance == null) {
                    instance = new ConnectUtil();
                }
            }
        }
        return instance;
    }

    public static void init(){
        if (instance == null) {
            instance = new ConnectUtil();
        }else {
            return;
        }
    }

    public GankIoService getGankIoService() {
        return gankIoService;
    }
}
