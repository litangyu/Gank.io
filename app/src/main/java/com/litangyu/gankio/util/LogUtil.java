/*
 * 描述
 * Created by: litangyu
 * 包含类名列表
 * 版本信息，版本号 v1.0
 * 创建日期 16/9/19
 * 版权声明
*/
package com.litangyu.gankio.util;

import android.util.Log;

/**
 * 描述
 * Log工具类
 * Created by: litangyu<br/>
 * Created on: 16/9/19 上午10:04
 */
public class LogUtil {

    private static boolean isDebug;

    public static void setDebugMode(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    public static void wtf(String tag, String msg) {
        if (isDebug)
            Log.wtf(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }
}
