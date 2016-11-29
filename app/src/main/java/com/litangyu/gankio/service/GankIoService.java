package com.litangyu.gankio.service;

import com.litangyu.gankio.bean.EveryDayForAndroidEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/29 上午10:48</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */

public interface GankIoService {
    @GET("data/android/{month}/{day}")
    Call<List<EveryDayForAndroidEntity>> getEveryDayForAndroid(
            @Path("month") String month, @Path("day") String day);
}
