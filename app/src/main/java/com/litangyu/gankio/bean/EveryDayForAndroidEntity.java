package com.litangyu.gankio.bean;

import java.util.List;

/**
 * 描述
 * <p>Version: v1.0</p>
 * <p>Created by: litangyu</p>
 * <p>Created on: 2016/11/29 上午9:07</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2016年 litangyu. All rights reserved.</p>
 * <p>Revision：</p>
 */
public class EveryDayForAndroidEntity extends ResultEntity{

    private List<AndroidEntity> results;

    public List<AndroidEntity> getResults() {
        return results;
    }

    public void setResults(List<AndroidEntity> results) {
        this.results = results;
    }
}
