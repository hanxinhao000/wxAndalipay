package com.xinhao.xhalipayandwxpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xinhao.xhpay.alipay.AliPayMain;
import com.xinhao.xhpay.alipay.xhlistener.AliPayListener;
import com.xinhao.xhpay.wxapi.XHWxMain;
import com.xinhao.xhpay.wxapi.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /***
         *
         *          XINHAO_HAN
         *
         *          请耐心跟着一步一步走,别心急,支付宝无所谓,微信一急就容易出错
         *
         *          本Demo操作步骤包括支付宝与微信支付
         *
         *          1.请按 ctrl + shift + f  或者 ctrl + shift + r 搜索 :  信息填写
         *
         *          2.请按 ctrl + shift + f  或者 ctrl + shift + r 搜索 :  回调的详细信息页面
         *
         *          理论步骤
         *
         *          1.在WxOrAliPayData中填写你正确的相关AppId与秘钥(支付宝OK了,直接就能调起来了)
         *
         *          2.Wx回调 请按 ctrl + shift + f  或者 ctrl + shift + r 搜索 :  回调的详细信息页面
         *
         *
         *
         *
         *
         *
         *
         */


        findViewById(R.id.alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AliPayMain.aliPay(MainActivity.this, "123456789", "0.01", new AliPayListener() {
                    @Override
                    public void payListener(int stat) {
                        /**
                         * stat 为1则表示支付成功,为2则表示支付失败
                         */


                        switch (stat) {
                            case AliPayListener.SUCCESS:
                                Toast.makeText(MainActivity.this, "开发者:支付成功", Toast.LENGTH_SHORT).show();
                                break;

                            case AliPayListener.FAILURE:
                                Toast.makeText(MainActivity.this, "开发者:支付失败", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
            }
        });

        findViewById(R.id.wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 注意
                 *
                 * orderMessage:为支付页面显示信息
                 *
                 * money:为金额    money单位为分!!!分  不是1元 比如100 = 1元  1 = 0.01元
                 *
                 * orderId:为32位以内的随机单号(方便记录)不能重复!不能重复!不能重复!不能重复!不能重复!
                 *
                 * 这个回调在WXPayEntryActivity里边,对没错就在那个MainActivity上边那个类,还在一个wxapi的包里
                 *
                 */
                XHWxMain.XhWxMain("1231321", "1", "3213246546546");
            }
        });

    }
}
