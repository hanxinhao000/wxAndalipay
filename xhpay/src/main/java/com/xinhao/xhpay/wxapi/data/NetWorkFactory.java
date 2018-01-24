package com.xinhao.xhpay.wxapi.data;

import android.util.Log;


import com.thoughtworks.xstream.XStream;
import com.xinhao.xhpay.WxOrAliPayData;
import com.xinhao.xhpay.wxapi.WxMain;
import com.xinhao.xhpay.wxapi.bean.OrederSendInfo;
import com.xinhao.xhpay.wxapi.utils.OkHttpUtils;
import com.xinhao.xhpay.wxapi.utils.WXpayUtils;

import java.io.UnsupportedEncodingException;

/**
 * XINHAO_HAN_WX_PAY
 */


public class NetWorkFactory {
    public interface Listerner {
        void Success(String data);

        void Faiulre(String data);
    }

    /**
     * 本地模拟 统一下单 生成微信预支付Id 这一步放在服务器端生成
     *
     * @param orederSendInfo
     * @param listerner
     *
     */
    public static void UnfiedOrder(OrederSendInfo orederSendInfo, final Listerner listerner) {

        //生成sign签名
        String sign = WXpayUtils.genSign(orederSendInfo);

        //生成所需参数，为xml格式
        orederSendInfo.setSign(sign.toUpperCase());
        XStream xstream = new XStream();
        xstream.alias("xml", OrederSendInfo.class);
        String xml = "";
        try {
            xml = new String(xstream.toXML(orederSendInfo).replaceAll("__", "_").getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
           // wxMessage1.message("支付失败,请重新尝试", -1, "0.0");
        }

        Log.e("xml---------------", "UnfiedOrder: " + xml);
        //调起接口，获取预支付ID
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.e("返回的数据", "onSuccess: " + response);
                String data = response;
                data = data.replaceAll("<!\\[CDATA\\[", "").replaceAll("]]>", "");
                listerner.Success(data);
            }

            @Override
            public void onFailure(Exception e) {
                listerner.Faiulre(e.toString());
            }
        };


        OkHttpUtils.post(WxOrAliPayData.UNIFIED_ORDER, resultCallback, xml);

    }
}
