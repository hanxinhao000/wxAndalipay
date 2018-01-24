package com.xinhao.xhpay.wxapi;

import android.util.Log;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.xinhao.xhpay.WxOrAliPayData;
import com.xinhao.xhpay.wxapi.bean.IPBean;
import com.xinhao.xhpay.wxapi.bean.OrederSendInfo;
import com.xinhao.xhpay.wxapi.bean.PrepayIdInfo;
import com.xinhao.xhpay.wxapi.data.NetWorkFactory;
import com.xinhao.xhpay.wxapi.utils.IPUtlis;
import com.xinhao.xhpay.wxapi.utils.UIUtils;
import com.xinhao.xhpay.wxapi.utils.WXpayUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * XINHAO_HAN 微信支付的主程序
 */

public class WxMain {


    private static PrepayIdInfo bean;


    public static void wxpay(final String orderMessage, String meony, final String orderId) {


        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);

        final String finalMeony = meony;
        IPUtlis.getIp(new IPUtlis.IPEner() {
            @Override
            public void ip(IPBean ip) {
                OrederSendInfo sendInfo = new OrederSendInfo(WxOrAliPayData.WE_CHAT_APP_ID, WxOrAliPayData.MCH_ID, WXpayUtils.genNonceStr(), orderMessage, orderId, finalMeony, ip.getIp(), WxOrAliPayData.URL, "APP");
                NetWorkFactory.UnfiedOrder(sendInfo, new NetWorkFactory.Listerner() {
                    @Override
                    public void Success(String data) {
                        //Toast.makeText(UIUtils.getContext(), "生成预支付Id成功", Toast.LENGTH_LONG).show();
                        // btn_pay.setEnabled(true);
                        XStream stream = new XStream();
                        stream.processAnnotations(PrepayIdInfo.class);
                        Log.e("------data数据", "Success: " + data);
                        try {
                            bean = (PrepayIdInfo) stream.fromXML(data);
                            WXpayUtils.Pay(bean.getPrepay_id());
                        } catch (Exception e) {
                            // wxMessage1.message("支付异常,请重新尝试", -11, "0.0");
                            Toast.makeText(UIUtils.getContext(), "出错了", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Faiulre(String data) {
                        Log.e("--生成失败!!", "Faiulre: " + data);
                    }
                });

                Log.e("数据集合", "pay: " + sendInfo);

            }
        });

    }


}
