package com.xinhao.xhpay.wxapi.utils;

import android.content.Context;

/**
 * Created by 14178 on 2018/1/24.
 */

public class UIUtils {

    public static Context mContext;

    public static void inntContext(Context context) {
        mContext = context;
    }


    public static Context getContext() {

        return mContext;
    }
}
