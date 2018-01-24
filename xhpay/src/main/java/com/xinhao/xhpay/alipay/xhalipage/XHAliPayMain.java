package com.xinhao.xhpay.alipay.xhalipage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.xinhao.xhpay.WxOrAliPayData;
import com.xinhao.xhpay.alipay.xhlistener.AliPayListener;

import java.util.Map;


/**
 * Created by 14178 on 2018/1/24.
 */

public class XHAliPayMain {

    private Activity mActivity;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private AliPayListener aliPayListener;

    /**
     *
     */


    public void mainPay(final Activity activity, String order, String money, AliPayListener aliPayListener) {
        mActivity = activity;
        this.aliPayListener = aliPayListener;


        if (TextUtils.isEmpty(WxOrAliPayData.ALI_PAY_APPID) || (TextUtils.isEmpty(WxOrAliPayData.ALI_PAY_PRIVATE_KEY))) {

            throw new RuntimeException("你必须填写支付宝密匙和支付宝ID");
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (WxOrAliPayData.ALI_PAY_PRIVATE_KEY.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(WxOrAliPayData.ALI_PAY_APPID, rsa2, order, money);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = WxOrAliPayData.ALI_PAY_PRIVATE_KEY;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

       // Log.e("支付宝", "mainPay: " + orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                       // Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                        if (aliPayListener != null) {
                            aliPayListener.payListener(1);
                        }
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                       // Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                        if (aliPayListener != null) {
                            aliPayListener.payListener(2);
                        }
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(mActivity,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mActivity,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
}
