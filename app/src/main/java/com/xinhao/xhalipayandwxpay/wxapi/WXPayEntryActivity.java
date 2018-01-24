package com.xinhao.xhalipayandwxpay.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xinhao.xhalipayandwxpay.R;
import com.xinhao.xhpay.WxOrAliPayData;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI mWeixinAPI;

    /**
     *
     * 这里的Wx回调才算是完整的,因为Wx只调用你App下的wxapi中的WXPayEntryActivity
     *
     *
     *          注意!!!!!!! 该Activity是最终调用的(微信支付的回调)
     *
     *          更多信息,请按 ctrl + shift + f  或者 ctrl + shift + r 搜索 :  回调的详细信息页面
     *
     *
     *
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.xinhao.xhpay.R.layout.activity_wxpay_entry);

        mWeixinAPI = WXAPIFactory.createWXAPI(this, WxOrAliPayData.WE_CHAT_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        /**
         *
         * 回调部分:
         *
         *  baseResp.errCode 解释与说明
         *
         *
         *
         *  code:0   //支付成功!
         *
         *  code:-1   //支付失败
         *
         *  code:-2   //支付取消
         *
         *
         */

        Log.e("支付结果", "onResp: " + baseResp.errCode);
    }
}
