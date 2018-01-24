package com.xinhao.xhpay.wxapi.utils;

import android.util.Log;

import com.google.gson.Gson;

import com.xinhao.xhpay.wxapi.bean.IPBean;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * XINHAO_HAN_WX_PAY
 */

public class IPUtlis {

    public static void getIp(final IPEner ipEner) {

        com.zhy.http.okhttp.OkHttpUtils.get().url("http://ip.chinaz.com/getip.aspx").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("IP地址:", "onResponse: " + response );
                IPBean ipBean = new Gson().fromJson(response, IPBean.class);

                String2Ip(ipBean, ipEner);
            }
        });

    }
    //var returnCitySN = {"cip": "219.144.202.177", "cid": "610100", "cname": "陕西省西安市"};


    public static void String2Ip(IPBean ipBean, IPEner ipEner) {

        ipEner.ip(ipBean);
    }

    public static interface IPEner {
        void ip(IPBean ip);
    }


}
