package com.xinhao.xhpay.alipay;

import android.app.Activity;

import com.xinhao.xhpay.alipay.xhalipage.XHAliPayMain;
import com.xinhao.xhpay.alipay.xhlistener.AliPayListener;

/**
 * 支付宝支付,主程序
 */

public class AliPayMain {


    //阿里支付

    /**
     * @param activity
     * @param orderMessage
     * @param money        第一个Activity,第二个你的订单信息,第三个金额
     */
    public static void aliPay(Activity activity, String orderMessage, String money, AliPayListener aliPayListener) {
        XHAliPayMain xhAliPayMain = new XHAliPayMain();
        xhAliPayMain.mainPay(activity, orderMessage, money, aliPayListener);
    }


}
