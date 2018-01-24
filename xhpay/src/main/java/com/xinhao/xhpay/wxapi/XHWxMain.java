package com.xinhao.xhpay.wxapi;

/**
 * Created by 14178 on 2018/1/24.
 */

public class XHWxMain {


    public static void XhWxMain(final String orderMessage, String meony, final String orderId){
        WxMain.wxpay(orderMessage,meony,orderId);
    }
}
