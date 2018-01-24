package com.xinhao.xhpay.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xinhao.xhpay.R;
import com.xinhao.xhpay.WxOrAliPayData;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI mWeixinAPI;


    /***
     *
     * @param savedInstanceState
     *
     *          回调的详细信息页面  点我!!!!!!点我!!!!!!点我!!!!!!点我!!!!!!点我!!!!!!点我!!!!!!点我!!!!!!
     *
     * 能在这里的哥们,相当于Wx支付已经完成的差不多了,在你的app下创建
     *
     * 1.创建wxapi文件夹
     *
     * 2.创建一个Activity,是Activity!!!!!
     *
     * 3.名称与本Activity名称相同为:WXPayEntryActivity
     *
     * 4.把当前Activity的代码   从 17 - 86行全部粘到你创建的Activity下边
     *
     * 5.在AndroidManifest里边复制这个主要是想加入一个android:exported="true"
     *
     *
     *              <activity
     *                      android:name=".wxapi.WXPayEntryActivity"
     *                      android:exported="true" />
     *
     * 6.完了,请看回调部分 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓在下边
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



        //此处是你的支付结果
        Log.e("支付结果", "onResp: " + baseResp.errCode);
    }
}
