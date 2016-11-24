/*
 * 描述
 * Created by: litangyu
 * 包含类名列表
 * 版本信息，版本号 v1.0
 * 创建日期 16/9/12
 * 版权声明
*/
package com.litangyu.gankio.util;


import com.litangyu.gankio.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述
 * Created by: litangyu<br/>
 * Created on: 16/9/12 下午10:50
 */
public class ActivityUtil {

    private static ActivityUtil mInstance;

    /**
     * 记录所有活动的Activity
     */
    public static final List<BaseActivity> mActivities = new LinkedList<>();

    public static synchronized ActivityUtil getInstance() {
        if (null == mInstance) {
            mInstance = new ActivityUtil();
        }
        return mInstance;
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity 界面
     */
    public static void pushActivtity(BaseActivity activity) {
        mActivities.add(activity);
    }

    /**
     * 将当前Activity推出栈中
     *
     * @param activity 界面
     */
    public static void closeActivity(BaseActivity activity) {
        if (null != activity) {
            mActivities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 获取当前栈顶Activity
     *
     * @return
     */
    public static BaseActivity getCurrentActivity() {
        if (mActivities.size() > 0) {
            return mActivities.get(mActivities.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * 退出栈中所有Activity除了一个Activity
     *
     * @param clazz 类
     */
    public static void closeAllExceptOne(Class clazz) {
        BaseActivity keepActivity = null;
        for (int i = 0; i < mActivities.size(); i++) {
            try {
                BaseActivity activity = mActivities.get(i);
                if (activity != null) {
                    if (activity.getClass().getSimpleName().equals(clazz.getSimpleName())) {
                        keepActivity = activity;
                    } else {
                        closeActivity(activity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mActivities.clear();
        if (keepActivity != null) {
            mActivities.add(keepActivity);
        }
    }

    /**
     * 退出栈中所有Activity除了指定Activity数组
     *
     * @param clazz 类数组
     */
    public static void closeAllException(Class[] clazz) {
        BaseActivity[] activities = new BaseActivity[clazz.length];
        for (BaseActivity activity : mActivities) {
            try {
                if (activity != null) {
                    boolean canClose = true;
                    for (int c = 0; c < clazz.length; c++) {
                        if (activity.getClass().getSimpleName().equals(clazz[c].getSimpleName())) {
                            canClose = false;
                            activities[c] = activity;
                        }
                    }
                    if (canClose) {
                        closeActivity(activity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mActivities.clear();
        for (BaseActivity activity : activities) {
            if (activity != null) {
                mActivities.add(activity);
            }
        }
    }

    /**
     * 退出栈中所有Activity
     */
    public static void closeAll() {
        for (BaseActivity activity : mActivities) {
            try {
                if (activity != null) {
                    closeActivity(activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mActivities.clear();
    }

    /**
     * 退出栈顶activity
     */
    public static void closeCurrent() {
        try {
            BaseActivity activity = getCurrentActivity();
            if (activity == null) {
                return;
            }
            closeActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 退出栈中指定Activity
     */
    public static void closeActivity(Class cls) {
        for (BaseActivity activity : mActivities) {
            try {
                if (activity != null && activity.getClass().getSimpleName().equals(cls
                        .getSimpleName())) {
                    closeActivity(activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
