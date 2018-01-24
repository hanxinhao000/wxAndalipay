package com.xinhao.xhpay.wxapi.utils;


import android.util.Log;
import android.widget.Toast;


import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xinhao.xhpay.WxOrAliPayData;
import com.xinhao.xhpay.wxapi.bean.OrederSendInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * XINHAO_HAN_WX_PAY
 */

public class WXpayUtils {

    private static IWXAPI iwxapi;
    private static PayReq req;

    public static IWXAPI getWXAPI() {
        if (iwxapi == null) {
            //通过WXAPIFactory创建IWAPI实例
            iwxapi = WXAPIFactory.createWXAPI(UIUtils.getContext(), null);
            req = new PayReq();
            //将应用的appid注册到微信
            iwxapi.registerApp(WxOrAliPayData.WE_CHAT_APP_ID);
        }
        return iwxapi;
    }

    //生成随机字符串
    public static String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    //获得时间戳
    private static long genTimeStamp() {
        Log.e("时间戳------", "genTimeStamp: " + System.currentTimeMillis() / 1000);
        return System.currentTimeMillis() / 1000;
    }

    //生成预支付随机签名
    public static String genSign(OrederSendInfo info) {
        StringBuffer sb = new StringBuffer(info.toString());
        if (WxOrAliPayData.WE_PRIVATE_KEY.equals("")) {
            Toast.makeText(UIUtils.getContext(), "APP_ID为空", Toast.LENGTH_LONG).show();
        }
        //拼接密钥
        sb.append("key=");
        sb.append(WxOrAliPayData.WE_PRIVATE_KEY);

        String appSign = MD5.getMessageDigest(sb.toString().getBytes());

        Log.e("Sign-------------", "genSign: " + appSign.toUpperCase());

        return appSign.toUpperCase();
    }

    //生成支付随机签名
    private static String genAppSign(List<OkHttpUtils.Param> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).key);
            sb.append('=');
            sb.append(params.get(i).value);
            sb.append('&');
        }
        //拼接密钥
        sb.append("key=");
        sb.append(WxOrAliPayData.WE_PRIVATE_KEY);

        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign.toUpperCase();
    }

    //生成支付参数
    private static void genPayReq(String prepayid) {
        req.appId = WxOrAliPayData.WE_CHAT_APP_ID;
        req.partnerId = WxOrAliPayData.MCH_ID;
        req.prepayId = prepayid;
        req.packageValue = "Sign=" + "WXPay";
        //req.packageValue = "Sign=" + prepayid;
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());

        List<OkHttpUtils.Param> signParams = new LinkedList<OkHttpUtils.Param>();
        signParams.add(new OkHttpUtils.Param("appid", req.appId));
        signParams.add(new OkHttpUtils.Param("noncestr", req.nonceStr));
        signParams.add(new OkHttpUtils.Param("package", req.packageValue));
        signParams.add(new OkHttpUtils.Param("partnerid", req.partnerId));
        signParams.add(new OkHttpUtils.Param("prepayid", req.prepayId));
        signParams.add(new OkHttpUtils.Param("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);
    }

    public static void Pay(String prepayid) {
        if (judgeCanGo()) {
            genPayReq(prepayid);
            iwxapi.registerApp(WxOrAliPayData.WE_CHAT_APP_ID);
            boolean b = iwxapi.sendReq(req);
            Log.e(TAG, "Pay: " + b);
        }
    }

    private static boolean judgeCanGo() {
        getWXAPI();
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(UIUtils.getContext(), "请先安装微信应用", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!iwxapi.isWXAppSupportAPI()) {
            Toast.makeText(UIUtils.getContext(), "请先更新微信应用", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
