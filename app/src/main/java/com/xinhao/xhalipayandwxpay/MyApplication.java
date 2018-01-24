package com.xinhao.xhalipayandwxpay;

import android.app.Application;

import com.xinhao.xhpay.wxapi.utils.UIUtils;

/**
 * Created by 14178 on 2018/1/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UIUtils.inntContext(this);

    }
}
